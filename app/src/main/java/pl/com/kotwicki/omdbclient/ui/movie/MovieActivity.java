package pl.com.kotwicki.omdbclient.ui.movie;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import pl.com.kotwicki.omdbclient.ui.BaseActivity;

import static pl.com.kotwicki.omdbclient.ui.movie.MovieFragment.EXTRA_MOVIE_IMDB_ID;

/**
 * Created by filipkotwicki on 26/05/15.
 */
public class MovieActivity extends BaseActivity {

    private static final String EXTRA_MOVIE_TITLE = "extra_movie_title";

    public static void start(final Context context, String movieTitle, String imdbID) {
        final Intent intent = new Intent(context, MovieActivity.class);
        intent.putExtra(EXTRA_MOVIE_TITLE, movieTitle);
        intent.putExtra(EXTRA_MOVIE_IMDB_ID, imdbID);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Fragment createFragment() {
        final Intent intent = getIntent();
        final String imdbID = intent.getStringExtra(EXTRA_MOVIE_IMDB_ID);

        return MovieFragment.create(imdbID);
    }

    @Override
    protected void setupToolbar(@NonNull Toolbar toolbar) {
        final Intent intent = getIntent();
        final String movieTitle = intent.getStringExtra(EXTRA_MOVIE_TITLE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(movieTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
