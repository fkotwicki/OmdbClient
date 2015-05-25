package pl.com.kotwicki.omdbclient.ui.search;

import pl.com.kotwicki.omdbclient.rest.MoviesService;

/**
 * Created by filipkotwicki on 25/05/15.
 */
interface SearchPresenter {

    void findMovie(MoviesService moviesService, String title);

    void onStop();

}
