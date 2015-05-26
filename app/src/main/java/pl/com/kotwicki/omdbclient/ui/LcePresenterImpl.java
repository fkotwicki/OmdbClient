package pl.com.kotwicki.omdbclient.ui;

import pl.com.kotwicki.omdbclient.model.UseCase;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by filipkotwicki on 26/05/15.
 */
public class LcePresenterImpl<CT> implements LcePresenter {

    protected LceView<CT> lceView;

    protected Subscription contentSubscription;

    public LcePresenterImpl(final LceView<CT> lceView) {
        this.lceView = lceView;
    }

    @Override
    public void getContent(UseCase useCase) {
        unsubscribe();
        lceView.showLoading();
        contentSubscription = useCase.execute(createContentSubscriber());
    }

    @Override
    public void onStop() {
        unsubscribe();
        lceView = null;
    }

    protected void onGotContent(final CT content) {
        lceView.showContent(content);
    }

    private void unsubscribe() {
        if (contentSubscription != null && !contentSubscription.isUnsubscribed()) {
            contentSubscription.unsubscribe();
            contentSubscription = null;
        }
    }

    private Subscriber<CT> createContentSubscriber() {
        return new Subscriber<CT>() {
            @Override
            public void onCompleted() {
                lceView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                lceView.hideLoading();
                lceView.showError(e);
            }

            @Override
            public void onNext(CT content) {
                onGotContent(content);
            }
        };
    }
}
