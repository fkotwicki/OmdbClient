package pl.com.kotwicki.omdbclient.ui;

/**
 * Loading-Content-Error view.
 * Created by filipkotwicki on 26/05/15.
 */
public interface LceView<T> {

    void showLoading();

    void hideLoading();

    void showError(Throwable e);

    void showContent(T content);

}
