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

public abstract class BaseFragment extends CommonFragment {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

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

        initViewModel();

        bindViewModel();
    }

    /**
     * provide ViewModel for this fragment
     * @param vmClass
     * @param isShareSameActivity false for default for this fragment,
     *                            true for share this VM with other fragment belong the same Activity
     * @return ViewModel for this fragment
     */
    protected <T extends ViewModel> T provideViewModel(Class<T> vmClass, boolean isShareSameActivity) {
        // in case we want to share ViewMode with other fragments belong the activity
        if(isShareSameActivity){
            return ViewModelProviders.of(getActivity(), mViewModelFactory).get(vmClass);
        }
        return ViewModelProviders.of(this, mViewModelFactory).get(vmClass);
    }

    /**
     * init view model
     */
    protected abstract void initViewModel();

    /**
     * bind data
     */
    protected abstract void bindViewModel();
}
