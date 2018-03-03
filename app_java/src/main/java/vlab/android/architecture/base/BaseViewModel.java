package vlab.android.architecture.base;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import vlab.android.architecture.MyApplication;


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
        mCompositeDisposable.clear();
    }

    public Context getAppContext(){
        return MyApplication.getInstance().getApplicationContext();
    }
}
