package pl.com.kotwicki.omdbclient.di;

import javax.inject.Singleton;

import dagger.Component;
import pl.com.kotwicki.omdbclient.rest.MoviesService;
import pl.com.kotwicki.omdbclient.rest.RestModule;

/**
 * Created by filipkotwicki on 25/05/15.
 */
@Singleton
@Component(modules = { RestModule.class })
public interface ApplicationComponent {

    MoviesService provideMoviesService();

}
