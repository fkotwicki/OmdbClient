package pl.com.kotwicki.omdbclient.ui.search;

import dagger.Component;
import pl.com.kotwicki.omdbclient.di.ApplicationComponent;
import pl.com.kotwicki.omdbclient.di.Fragment;

/**
 * Created by filipkotwicki on 25/05/15.
 */
@Fragment
@Component(dependencies = ApplicationComponent.class)
public interface SearchComponent {

    void inject(SearchFragment searchFragment);

    void inject(SearchActivity searchActivity);

}
