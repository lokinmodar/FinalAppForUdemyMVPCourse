package br.com.theoldpinkeye.finalappforudemymvpcourse.topmovies;

import javax.inject.Singleton;

import br.com.theoldpinkeye.finalappforudemymvpcourse.http.MoreInfoApiService;
import br.com.theoldpinkeye.finalappforudemymvpcourse.http.MovieApiService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Just Us on 01/12/2017.
 */

@Module
public class TopMoviesModule {

    @Provides
    public TopMoviesActivityMVP.Presenter provideTopMoviesActivityPresenter(TopMoviesActivityMVP.Model topMoviesModel) {
        return new TopMoviesPresenter(topMoviesModel);
    }

    @Provides
    public TopMoviesActivityMVP.Model provideTopMoviesActivityModel(Repository repository) {
        return new TopMoviesModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(MovieApiService movieApiService, MoreInfoApiService moreInfoApiService) {
        return new TopMoviesRepository(movieApiService, moreInfoApiService);
    }


}