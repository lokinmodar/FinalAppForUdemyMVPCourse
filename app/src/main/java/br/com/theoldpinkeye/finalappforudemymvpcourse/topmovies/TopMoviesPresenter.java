package br.com.theoldpinkeye.finalappforudemymvpcourse.topmovies;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Just Us on 29/11/2017.
 */

public class TopMoviesPresenter implements TopMoviesActivityMVP.Presenter {

    private TopMoviesActivityMVP.View view;
    private Disposable disposable = null;
    private TopMoviesActivityMVP.Model model; //is an Observable


    public TopMoviesPresenter(TopMoviesActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() { //changed to comply with RxJava2. They used RxJava1 in the course examples
        disposable = model.result().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<ViewModel>() {


            @Override
            public void onNext(ViewModel viewModel) {
                if (view != null){
                    view.updateData(viewModel);
                }

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (view != null){
                    view.showSnackbar("Error getting movies");
                }
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void rxDispose() { //Updated to RxJava2 terminology
        if (disposable != null) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
        }

    }

    @Override
    public void setView(TopMoviesActivityMVP.View view) {
        this.view = view;

    }
}
