package br.com.theoldpinkeye.finalappforudemymvpcourse.topmovies;


import java.util.ArrayList;
import java.util.List;

import br.com.theoldpinkeye.finalappforudemymvpcourse.http.MoreInfoApiService;
import br.com.theoldpinkeye.finalappforudemymvpcourse.http.MovieApiService;
import br.com.theoldpinkeye.finalappforudemymvpcourse.http.apimodel.OmdbApi;
import br.com.theoldpinkeye.finalappforudemymvpcourse.http.apimodel.Result;
import br.com.theoldpinkeye.finalappforudemymvpcourse.http.apimodel.TopRated;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static br.com.theoldpinkeye.finalappforudemymvpcourse.http.ApiModuleForInfo.API_KEY;

/**
 * Created by Just Us on 01/12/2017.
 */


public class TopMoviesRepository implements Repository {

    private MovieApiService movieApiService;
    private MoreInfoApiService moreInfoApiService;
    private List<String> countries;
    private List<Result> results;
    private long timestamp;


    private static final long STALE_MS = 60 * 1000; // Data is stale after 60 seconds

    public TopMoviesRepository(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService) {
        this.moreInfoApiService = moreInfoApiService;
        this.timestamp = System.currentTimeMillis();
        this.movieApiService = movieApiService;
        countries = new ArrayList<>();
        results = new ArrayList<>();

    }

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    @Override
    public Observable<Result> getResultsFromMemory() {

        if (isUpToDate()) {
            return Observable.fromIterable(results);
        } else {
            timestamp = System.currentTimeMillis();
            results.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Result> getResultsFromNetwork() {

        Observable<TopRated> topRatedObservable = movieApiService.getTopRatedMovies(1).concatWith(movieApiService.getTopRatedMovies(2)).concatWith(movieApiService.getTopRatedMovies(3));

        return topRatedObservable.concatMap(new Function<TopRated, Observable<Result>>() {
            @Override
            public Observable<Result> apply(TopRated topRated) {
                return Observable.fromIterable(topRated.results);
            }
        }).doOnNext(new Consumer<Result>() {
            @Override
            public void accept(Result result) {
                results.add(result);
            }
        });
    }

    @Override
    public Observable<String> getCountriesFromMemory() {

        if (isUpToDate()) {
            return Observable.fromIterable(countries);
        } else {
            timestamp = System.currentTimeMillis();
            countries.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<String> getCountriesFromNetwork() {

        return getResultsFromNetwork().concatMap(new Function<Result, Observable<OmdbApi>>() {
            @Override
            public Observable<OmdbApi> apply(Result result) {
                return moreInfoApiService.getCountry(result.title, API_KEY);
            }
        }).concatMap(new Function<OmdbApi, Observable<String>>() {
            @Override
            public Observable<String> apply(OmdbApi omdbApi) {
                return Observable.just(omdbApi.getCountry());
            }
        }).doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) {
                countries.add(s);
            }
        }).doFinally(() -> {//Avoids Socket closed Exception!
            isUpToDate();
        });

    }

    @Override
    public Observable<String> getCountryData() {

        return getCountriesFromMemory().switchIfEmpty(getCountriesFromNetwork());

    }

    @Override
    public Observable<Result> getResultData() {
        return getResultsFromMemory().switchIfEmpty(getResultsFromNetwork());
    }
}