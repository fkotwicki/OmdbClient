package pl.com.kotwicki.omdbclient.ui.search;

import pl.com.kotwicki.omdbclient.model.SearchMovieUseCase;
import pl.com.kotwicki.omdbclient.rest.MoviesService;
import pl.com.kotwicki.omdbclient.rest.model.MovieSearchResult;
import pl.com.kotwicki.omdbclient.ui.LceView;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by filipkotwicki on 25/05/15.
 */
class SearchPresenterImpl implements SearchPresenter {

    private LceView<MovieSearchResult> searchView;

    private Subscription searchSubscription;

    SearchPresenterImpl(LceView<MovieSearchResult> searchView) {
        this.searchView = searchView;
    }

    @Override
    public void findMovie(MoviesService moviesService, String title) {
        unsubscribe();
        searchView.showLoading();
        searchSubscription = new SearchMovieUseCase(moviesService, title).execute(createSearchSubscriber());
    }

    @Override
    public void onStop() {
        unsubscribe();
        searchView = null;
    }

    private void unsubscribe() {
        if (searchSubscription != null && !searchSubscription.isUnsubscribed()) {
            searchSubscription.unsubscribe();
            searchSubscription = null;
        }
    }

    private Subscriber<MovieSearchResult> createSearchSubscriber() {
        return new Subscriber<MovieSearchResult>() {
            @Override
            public void onCompleted() {
                searchView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                searchView.hideLoading();
                searchView.showError(e);
            }

            @Override
            public void onNext(MovieSearchResult movieSearchResult) {
                searchView.showContent(movieSearchResult);
            }
        };
    }
}
