package pl.com.kotwicki.omdbclient.di;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by filipkotwicki on 26/05/15.
 */
@Module
public class ApplicationModule {

    private final Bus bus;

    public ApplicationModule(final Bus bus) {
        this.bus = bus;
    }

    @Provides
    @Singleton
    public Bus provideEventBus() {
        return bus;
    }

}
