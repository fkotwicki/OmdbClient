package pl.com.kotwicki.omdbclient.ui;

import pl.com.kotwicki.omdbclient.model.UseCase;

/**
 * Created by filipkotwicki on 26/05/15.
 */
public interface LcePresenter {

    void getContent(UseCase useCase);

    void onStop();

}
