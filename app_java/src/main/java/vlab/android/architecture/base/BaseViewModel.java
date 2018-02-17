package vlab.android.architecture.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by Vinh Tran on 2/15/18.
 */

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    protected void addSubscriptions(Disposable... disposables){
        for (Disposable disposable: disposables) {
            mCompositeDisposable.add(disposable);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
