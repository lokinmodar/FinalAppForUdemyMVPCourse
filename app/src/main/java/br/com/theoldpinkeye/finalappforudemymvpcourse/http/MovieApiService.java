package br.com.theoldpinkeye.finalappforudemymvpcourse.http;

import br.com.theoldpinkeye.finalappforudemymvpcourse.http.apimodel.TopRated;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Just Us on 01/12/2017.
 */

public interface MovieApiService {

    @GET("top_rated")
    Observable<TopRated> getTopRatedMovies(@Query("page") Integer page);





}