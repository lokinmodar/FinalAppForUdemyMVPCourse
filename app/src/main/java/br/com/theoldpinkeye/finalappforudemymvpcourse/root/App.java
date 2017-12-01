package br.com.theoldpinkeye.finalappforudemymvpcourse.root;


import android.app.Application;

import br.com.theoldpinkeye.finalappforudemymvpcourse.http.ApiModuleForInfo;
import br.com.theoldpinkeye.finalappforudemymvpcourse.http.ApiModuleForName;
import br.com.theoldpinkeye.finalappforudemymvpcourse.topmovies.TopMoviesModule;

/**
 * Created by Just Us on 01/12/2017.
 */

public class App extends Application {


    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModuleForName(new ApiModuleForName())
                .topMoviesModule(new TopMoviesModule())
                .apiModuleForInfo(new ApiModuleForInfo())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

}
