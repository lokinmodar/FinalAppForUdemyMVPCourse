package br.com.theoldpinkeye.finalappforudemymvpcourse.http;

import br.com.theoldpinkeye.finalappforudemymvpcourse.http.apimodel.OmdbApi;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Just Us on 01/12/2017.
 */

public interface MoreInfoApiService {

    @GET("/")
    Observable<OmdbApi> getCountry(@Query("t") String title);


}