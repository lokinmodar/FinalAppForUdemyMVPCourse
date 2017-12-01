package br.com.theoldpinkeye.finalappforudemymvpcourse.topmovies;

import br.com.theoldpinkeye.finalappforudemymvpcourse.http.apimodel.Result;
import io.reactivex.Observable;

/**
 * Created by Just Us on 01/12/2017.
 */

public interface Repository {

    Observable<Result> getResultsFromMemory();

    Observable<Result> getResultsFromNetwork();

    Observable<String> getCountriesFromMemory();

    Observable<String> getCountriesFromNetwork();

    Observable<String> getCountryData();

    Observable<Result> getResultData();

}