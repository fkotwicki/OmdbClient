package pl.com.kotwicki.omdbclient.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import pl.com.kotwicki.omdbclient.AppController;
import pl.com.kotwicki.omdbclient.di.ApplicationComponent;

/**
 * Created by filipkotwicki on 25/05/15.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(AppController.from(getActivity()).applicationComponent());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(getLayoutId(), null);
        ButterKnife.inject(this, rootView);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    protected void initView(@NonNull final View rootView) {
        // template method
    }

    protected void inject(@NonNull ApplicationComponent applicationComponent) {
        // template method
    }

    protected void showShortToast(final String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    protected abstract int getLayoutId();

}
