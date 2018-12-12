package vlab.android.architecture.base;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import vlab.android.common.ui.CommonFragment;
import vlab.android.common.util.LogUtils;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public abstract class BaseFragment extends CommonFragment {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    protected BaseErrorHandler mErrorHandler;
    private Unbinder mUnBinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(getClass().getSimpleName(), ">>> onCreate");
        mErrorHandler = getErrorHandler();
    }

    protected BaseErrorHandler getErrorHandler() {
        return new BaseErrorHandler();
    }

    protected void showErrorMsg(Throwable throwable) {
        Toast.makeText(getContext(), getErrorHandler().parseError(throwable), Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        LogUtils.d(getClass().getSimpleName(), ">>> onCreateView: ");

        initViewModel();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.d(getClass().getSimpleName(), ">>> onViewCreated: ");
        bindViewModel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    /**
     * provide ViewModel for this fragment
     * @param vmClass
     * @return ViewModel for this fragment
     */
    protected <T extends BaseViewModel> T provideViewModel(Class<T> vmClass) {
        return provideViewModel(vmClass, false);
    }

    /**
     * provide ViewModel for this fragment
     * @param vmClass
     * @param isShareSameActivity false for default for this fragment,
     *                            true for share this VM with other fragment belong the same Activity
     * @return ViewModel for this fragment
     */
    protected <T extends BaseViewModel> T provideViewModel(Class<T> vmClass, boolean isShareSameActivity) {
        // in case we want to share ViewMode with other fragments belong the activity
        T viewModel;
        if(isShareSameActivity){
            viewModel = ViewModelProviders.of(getActivity(), mViewModelFactory).get(vmClass);
        } else {
            viewModel = ViewModelProviders.of(this, mViewModelFactory).get(vmClass);
        }

        viewModel.setLifeCycleOwner(this);

        return viewModel;
    }

    /**
     * init view model, was called in onViewCreated()
     */
    protected abstract void initViewModel();

    /**
     * bind data, was called in onViewCreated() after initViewModel()
     */
    protected abstract void bindViewModel();
}
