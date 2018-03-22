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

import vlab.android.common.ui.CommonFragment;
import vlab.android.common.util.LogUtils;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public abstract class BaseFragment<T extends ViewModel> extends CommonFragment {

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
        View view = super.onCreateView(inflater, container, savedInstanceState);
        LogUtils.d(getClass().getSimpleName(), ">>> onCreateView: ");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = provideViewModel();
        bindViewModel();
    }

    /**
     * provide view model
     * @return
     */
    protected T provideViewModel() {
        // in case we want to share ViewMode with other fragments belong the activity
        if(isShareViewModel()){
            return ViewModelProviders.of(getActivity(), mViewModelFactory).get(getViewModelClass());
        }
        return ViewModelProviders.of(this, mViewModelFactory).get(getViewModelClass());
    }

    protected boolean isShareViewModel() {
        return false;
    }

    protected abstract Class<T> getViewModelClass();

    /**
     * bind data
     */
    protected abstract void bindViewModel();
}
