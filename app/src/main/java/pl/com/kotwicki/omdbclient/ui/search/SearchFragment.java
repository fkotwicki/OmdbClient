package pl.com.kotwicki.omdbclient.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.InjectView;
import pl.com.kotwicki.omdbclient.R;
import pl.com.kotwicki.omdbclient.di.ApplicationComponent;
import pl.com.kotwicki.omdbclient.rest.MoviesService;
import pl.com.kotwicki.omdbclient.rest.model.MovieSearchResult;
import pl.com.kotwicki.omdbclient.ui.BaseFragment;

/**
 * Created by filipkotwicki on 25/05/15.
 */
public class SearchFragment extends BaseFragment implements SearchView, SearchForm.Listener {

    @Inject
    MoviesService moviesService;

    @InjectView(R.id.fragment_search_dummy)
    TextView dummy;

    @InjectView(R.id.fragment_search_form)
    SearchForm searchFormView;

    private SearchPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    @Override
    protected void inject(@NonNull ApplicationComponent applicationComponent) {
        DaggerSearchComponent.builder().applicationComponent(applicationComponent).build().inject(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(Throwable e) {
        Log.e("SearchFragment", "Fetch error", e);
    }

    @Override
    public void showSearchResult(MovieSearchResult result) {
        if(result.isEmpty()) {
            showShortToast(getString(R.string.movie_not_found));
        } else {
            final StringBuilder sb = new StringBuilder();
            for (MovieSearchResult.Entry entry : result.entries) {
                sb.append(entry.toString() + "\n");
            }
            dummy.setText(sb.toString());
        }
    }

    @Override
    public void onRequestSearch(String searchText) {
        presenter.findMovie(moviesService, searchText);
    }

}
