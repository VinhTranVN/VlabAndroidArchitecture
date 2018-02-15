package vlab.android.architecture.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

}
