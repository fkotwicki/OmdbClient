package pl.com.kotwicki.omdbclient.ui;

/**
 * Loading-Content-Error view.
 * CT - content type
 * Created by filipkotwicki on 26/05/15.
 */
public interface LceView<CT> {

    void showLoading();

    void hideLoading();

    void showError(Throwable e);

    void showContent(CT content);

}
