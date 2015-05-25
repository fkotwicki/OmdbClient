package pl.com.kotwicki.omdbclient.ui.search;

import pl.com.kotwicki.omdbclient.rest.model.MovieSearchResult;

/**
 * Created by filipkotwicki on 25/05/15.
 */
interface SearchView {

    void showProgress();

    void hideProgress();

    void showError(Throwable e);

    void showSearchResult(MovieSearchResult result);

}
