package vlab.android.architecture.base;

import android.arch.lifecycle.ViewModel;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public class BaseViewModel extends ViewModel {

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    protected void addSubscriptions(Subscription... subscriptions){
        for (Subscription subscription: subscriptions) {
            mCompositeSubscription.add(subscription);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeSubscription.clear();
    }
}
