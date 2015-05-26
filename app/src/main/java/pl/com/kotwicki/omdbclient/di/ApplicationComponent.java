package pl.com.kotwicki.omdbclient.di;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import pl.com.kotwicki.omdbclient.rest.MoviesService;
import pl.com.kotwicki.omdbclient.rest.RestModule;

/**
 * Created by filipkotwicki on 25/05/15.
 */
@Singleton
@Component(modules = {ApplicationModule.class, RestModule.class})
public interface ApplicationComponent {

    Bus provideEventBus();

    MoviesService provideMoviesService();

}
