package pl.com.kotwicki.omdbclient.rest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by filipkotwicki on 25/05/15.
 */
@Module
public class RestModule {

    private static final String MOVIES_SERVICE_ENDPOINT = "http://www.omdbapi.com";

    private final MoviesService moviesService;

    public RestModule() {
        moviesService = new RestAdapter.Builder().setEndpoint(MOVIES_SERVICE_ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL).build().create(MoviesService.class);
    }

    @Provides
    @Singleton
    public MoviesService provideMoviesService() {
        return moviesService;
    }

}
