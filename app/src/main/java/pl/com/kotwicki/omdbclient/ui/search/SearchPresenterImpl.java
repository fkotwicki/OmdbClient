package pl.com.kotwicki.omdbclient.ui.search;

import pl.com.kotwicki.omdbclient.model.SearchMovieUseCase;
import pl.com.kotwicki.omdbclient.rest.MoviesService;
import pl.com.kotwicki.omdbclient.rest.model.MovieSearchResult;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by filipkotwicki on 25/05/15.
 */
class SearchPresenterImpl implements SearchPresenter {

    private final SearchView searchView;

    private Subscription searchSubscription;

    SearchPresenterImpl(SearchView searchView) {
        this.searchView = searchView;
    }

    @Override
    public void findMovie(MoviesService moviesService, String title) {
        unsubscribe();
        searchView.showProgress();
        searchSubscription = new SearchMovieUseCase(moviesService, title).execute(createSearchSubscriber());
    }

    @Override
    public void onStop() {
        unsubscribe();
    }

    private void unsubscribe() {
        if(searchSubscription != null && !searchSubscription.isUnsubscribed()) {
            searchSubscription.unsubscribe();
            searchSubscription = null;
        }
    }

    private Subscriber<MovieSearchResult> createSearchSubscriber() {
        return new Subscriber<MovieSearchResult>() {
            @Override
            public void onCompleted() {
                searchView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                searchView.hideProgress();
                searchView.showError(e);
            }

            @Override
            public void onNext(MovieSearchResult movieSearchResult) {
                searchView.showSearchResult(movieSearchResult);
            }
        };
    }
}
