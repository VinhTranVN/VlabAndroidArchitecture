package vlab.android.architecture.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import vlab.android.architecture.MyApplication;
import vlab.android.architecture.di.AppComponent;
import vlab.android.common.ui.CommonFragment;
import vlab.android.common.util.LogUtils;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public abstract class BaseFragment<T extends ViewModel> extends CommonFragment {

    protected T mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(getClass().getSimpleName(), ">>> onCreate");
        inject(MyApplication.getInstance().getAppComponent());
        mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(getViewModelClass());
    }

    protected abstract void inject(AppComponent appComponent);

    protected abstract ViewModelProvider.Factory getViewModelFactory();

    protected abstract Class<T> getViewModelClass();
}
