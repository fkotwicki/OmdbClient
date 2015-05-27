package pl.com.kotwicki.omdbclient.ui;

import java.lang.ref.WeakReference;

import pl.com.kotwicki.omdbclient.model.UseCase;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by filipkotwicki on 26/05/15.
 */
public class LcePresenterImpl<CT> implements LcePresenter {

    protected WeakReference<LceView<CT>> lceViewRef;

    protected Subscription contentSubscription;

    public LcePresenterImpl(final LceView<CT> lceView) {
        this.lceViewRef = new WeakReference<>(lceView);
    }

    @Override
    public void getContent(UseCase useCase) {
        unsubscribe();
        if(lceViewRef.get() != null) {
            lceViewRef.get().showLoading();
        }
        contentSubscription = useCase.execute(createContentSubscriber());
    }

    @Override
    public void onStop() {
        unsubscribe();
    }

    protected void onGotContent(final CT content) {
        if(lceViewRef.get() != null) {
            lceViewRef.get().showContent(content);
        }
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
                if(lceViewRef.get()!= null) {
                    lceViewRef.get().hideLoading();
                }
            }

            @Override
            public void onError(Throwable e) {
                final LceView view = lceViewRef.get();
                if(view != null) {
                    view.hideLoading();
                    view.showError(e);
                }
            }

            @Override
            public void onNext(CT content) {
                onGotContent(content);
            }
        };
    }


}
