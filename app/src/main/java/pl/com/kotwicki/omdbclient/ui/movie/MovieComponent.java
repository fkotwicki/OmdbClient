package pl.com.kotwicki.omdbclient.ui.movie;

import dagger.Component;
import pl.com.kotwicki.omdbclient.di.ApplicationComponent;
import pl.com.kotwicki.omdbclient.di.Custom;

/**
 * Created by filipkotwicki on 26/05/15.
 */
@Custom
@Component(dependencies = ApplicationComponent.class)
public interface MovieComponent {

    void inject(MovieFragment movieFragment);

}
