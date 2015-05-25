package pl.com.kotwicki.omdbclient.rest;

import pl.com.kotwicki.omdbclient.rest.model.MovieSearchResult;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by filipkotwicki on 25/05/15.
 */
public interface MoviesService {

    @GET("/")
    Observable<MovieSearchResult> findMovie(@Query("s") String title);

}
