package pl.com.kotwicki.omdbclient.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import pl.com.kotwicki.omdbclient.R;

/**
 * Created by filipkotwicki on 25/05/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pane);

        final FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.activity_single_pane_container, createFragment()).commit();

        setupToolbar((Toolbar) findViewById(R.id.toolbar));
    }

    protected void setupToolbar(@NonNull final Toolbar toolbar) {
        // template method
    }

    protected abstract Fragment createFragment();

}
