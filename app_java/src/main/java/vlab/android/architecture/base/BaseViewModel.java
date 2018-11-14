package vlab.android.architecture.base;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import vlab.android.architecture.MyApplication;


/**
 * Created by Vinh Tran on 2/15/18.
 */

public abstract class BaseViewModel extends ViewModel {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    protected LifecycleOwner mLifeCycleOwner;

    protected void addSubscriptions(Disposable... disposables){
        mCompositeDisposable.addAll(disposables);
    }

    protected BaseViewModel(){

    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.clear();
        mLifeCycleOwner = null;
        super.onCleared();
    }

    public Context getAppContext(){
        return MyApplication.getInstance().getApplicationContext();
    }

    public void setLifeCycleOwner(LifecycleOwner lifeCycleOwner) {
        mLifeCycleOwner = lifeCycleOwner;
    }

    public LifecycleOwner getLifeCycleOwner() {
        return mLifeCycleOwner;
    }
}
