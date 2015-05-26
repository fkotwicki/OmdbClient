package pl.com.kotwicki.omdbclient.ui.movie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.InjectView;
import pl.com.kotwicki.omdbclient.R;
import pl.com.kotwicki.omdbclient.di.ApplicationComponent;
import pl.com.kotwicki.omdbclient.model.GetMovieDetailsUseCase;
import pl.com.kotwicki.omdbclient.rest.MoviesService;
import pl.com.kotwicki.omdbclient.rest.model.MovieDetails;
import pl.com.kotwicki.omdbclient.ui.BaseFragment;
import pl.com.kotwicki.omdbclient.ui.LcePresenter;
import pl.com.kotwicki.omdbclient.ui.LcePresenterImpl;
import pl.com.kotwicki.omdbclient.ui.LceView;

/**
 * Created by filipkotwicki on 26/05/15.
 */
public class MovieFragment extends BaseFragment implements LceView<MovieDetails> {

    public static final String EXTRA_MOVIE_IMDB_ID = "extra_imdb_id";

    @InjectView(R.id.fragment_movie_details_view)
    MovieDetailsView movieDetailsView;

    @Inject
    MoviesService moviesService;

    private MaterialDialog progressDialog;

    private LcePresenter movieDetailsPresenter;

    public static MovieFragment create(final String imdbID) {
        final Bundle args = new Bundle();
        args.putString(EXTRA_MOVIE_IMDB_ID, imdbID);

        final MovieFragment instance = new MovieFragment();
        instance.setArguments(args);

        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetailsPresenter = new LcePresenterImpl<>(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle args = getArguments();
        final String imdbID = args.getString(EXTRA_MOVIE_IMDB_ID);
        movieDetailsPresenter.getContent(new GetMovieDetailsUseCase(moviesService, imdbID));
    }

    @Override
    public void onDestroyView() {
        dismissProgressDialog();
        super.onDestroyView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie_details;
    }

    @Override
    protected void inject(@NonNull ApplicationComponent applicationComponent) {
        DaggerMovieComponent.builder().applicationComponent(applicationComponent).build().inject(this);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void showError(Throwable e) {
        showShortToast(getString(R.string.fetch_error));
    }

    @Override
    public void showContent(MovieDetails content) {
        Picasso.with(getActivity().getApplicationContext()).load(content.posterURL).placeholder(R.drawable.placeholder).into(movieDetailsView.getPosterImageView());
        movieDetailsView.fillDetails(content);
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            final MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());
            builder.progress(true, 0);
            builder.cancelable(false);
            builder.content(getString(R.string.loading_movie) + "...");

            progressDialog = builder.build();
        }

        progressDialog.show();
    }

    private void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
