package pl.com.kotwicki.omdbclient.ui.search;

import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.InjectView;
import pl.com.kotwicki.omdbclient.R;
import pl.com.kotwicki.omdbclient.di.ApplicationComponent;
import pl.com.kotwicki.omdbclient.ui.BaseActivity;
import pl.com.kotwicki.omdbclient.ui.search.event.ToggleProgressIndicatorEvent;

/**
 * Created by filipkotwicki on 25/05/15.
 */
public class SearchActivity extends BaseActivity {

    @InjectView(R.id.activity_progress_bar)
    ProgressBar progressBar;

    @Inject
    Bus eventBus;

    @Subscribe
    public void onToggleProgressIndicator(ToggleProgressIndicatorEvent event) {
        progressBar.setVisibility(event.show ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Override
    protected Fragment createFragment() {
        return new SearchFragment();
    }

    @Override
    protected void setupToolbar(@NonNull Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.find_a_movie));
    }

    @Override
    protected void inject(@NonNull ApplicationComponent applicationComponent) {
        DaggerSearchComponent.builder().applicationComponent(applicationComponent).build().inject(this);
    }
}
