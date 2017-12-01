package br.com.theoldpinkeye.finalappforudemymvpcourse.root;

import javax.inject.Singleton;

import br.com.theoldpinkeye.finalappforudemymvpcourse.http.ApiModuleForInfo;
import br.com.theoldpinkeye.finalappforudemymvpcourse.http.ApiModuleForName;
import br.com.theoldpinkeye.finalappforudemymvpcourse.topmovies.TopMoviesActivity;
import br.com.theoldpinkeye.finalappforudemymvpcourse.topmovies.TopMoviesModule;
import dagger.Component;

/**
 * Created by Just Us on 01/12/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ApiModuleForName.class, ApiModuleForInfo.class,TopMoviesModule.class})
public interface ApplicationComponent {

    void inject(TopMoviesActivity target);

}