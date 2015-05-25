package pl.com.kotwicki.omdbclient.ui.search;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import pl.com.kotwicki.omdbclient.R;
import pl.com.kotwicki.omdbclient.ui.BaseActivity;

/**
 * Created by filipkotwicki on 25/05/15.
 */
public class SearchActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return new SearchFragment();
    }

    @Override
    protected void setupToolbar(@NonNull Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.find_a_movie));
    }
}
