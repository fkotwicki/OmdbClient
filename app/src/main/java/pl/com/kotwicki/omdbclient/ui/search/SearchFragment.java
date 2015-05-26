package pl.com.kotwicki.omdbclient.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.InjectView;
import pl.com.kotwicki.omdbclient.R;
import pl.com.kotwicki.omdbclient.di.ApplicationComponent;
import pl.com.kotwicki.omdbclient.rest.MoviesService;
import pl.com.kotwicki.omdbclient.rest.model.MovieSearchResult;
import pl.com.kotwicki.omdbclient.ui.BaseFragment;
import pl.com.kotwicki.omdbclient.ui.LceView;
import pl.com.kotwicki.omdbclient.ui.search.event.ToggleProgressIndicatorEvent;

/**
 * Created by filipkotwicki on 25/05/15.
 */
public class SearchFragment extends BaseFragment implements SearchForm.Listener, LceView<MovieSearchResult>, SearchResultAdapter.Listener {

    @InjectView(R.id.fragment_search_list)
    RecyclerView searchResultView;

    @InjectView(R.id.fragment_search_form)
    SearchForm searchFormView;

    @Inject
    MoviesService moviesService;

    @Inject
    Bus eventBus;

    private SearchResultAdapter searchResultAdapter;

    private SearchPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchResultAdapter = new SearchResultAdapter(getActivity(), this);
        presenter = new SearchPresenterImpl(this);
    }

    @Override
    public void onDestroy() {
        presenter.onStop();
        presenter = null;
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView(@NonNull View rootView) {
        searchFormView.setFormListener(this);
        searchResultView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchResultView.setAdapter(searchResultAdapter);
    }

    @Override
    protected void inject(@NonNull ApplicationComponent applicationComponent) {
        DaggerSearchComponent.builder().applicationComponent(applicationComponent).build().inject(this);
    }

    @Override
    public void showLoading() {
        eventBus.post(new ToggleProgressIndicatorEvent(true));
    }

    @Override
    public void hideLoading() {
        eventBus.post(new ToggleProgressIndicatorEvent(false));
    }

    @Override
    public void showError(Throwable e) {
        showShortToast("Fetch error");
    }

    @Override
    public void showContent(MovieSearchResult content) {
        if (content.isEmpty()) {
            showShortToast(getString(R.string.movie_not_found));
        }

        searchResultAdapter.update(content);
    }

    @Override
    public void onRequestSearch(String searchText) {
        presenter.findMovie(moviesService, searchText);
    }

    @Override
    public void onMovieSelected(MovieSearchResult.Entry movieSearchResultEntry) {

    }

}
