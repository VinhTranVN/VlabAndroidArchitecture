package vlab.android.architecture.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import vlab.android.architecture.di.util.Injectable;
import vlab.android.common.ui.CommonFragment;
import vlab.android.common.util.LogUtils;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public abstract class BaseFragment<T extends ViewModel> extends CommonFragment implements Injectable {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    protected T mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(getClass().getSimpleName(), ">>> onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d(getClass().getSimpleName(), ">>> onCreateView: ");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtils.d(getClass().getSimpleName(), ">>> onActivityCreated: ");
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(getViewModelClass());
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract Class<T> getViewModelClass();
}
