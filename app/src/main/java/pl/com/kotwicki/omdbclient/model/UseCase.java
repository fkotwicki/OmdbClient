package pl.com.kotwicki.omdbclient.model;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by filipkotwicki on 25/05/15.
 */
public interface UseCase {

    Subscription execute(Subscriber subscriber);

}
