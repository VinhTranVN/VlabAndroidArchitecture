package vlab.android.common.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vlab.android.common.util.LogUtils;

/**
* Created by Vinh Tran on 2/7/18.
*/
public abstract class CommonFragment extends Fragment {

    protected CommonProgressDialogFragment mProgressDialogFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgressDialogFragment = CommonProgressDialogFragment.newInstance(null, 1);

        initView(view);
        bindViewModel();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogUtils.i(getClass().getSimpleName(), ">>> onLowMemory: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.i(getClass().getSimpleName(), ">>> onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.i(getClass().getSimpleName(), ">>> onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(getClass().getSimpleName(), ">>> onDestroy: ");
    }

    /**
     * declare layout resource id
     */
    protected abstract int getLayoutRes();

    /**
     * init view
     * @param rootView
     */
    protected abstract void initView(View rootView);

    /**
     * bind data
     */
    protected abstract void bindViewModel();

    public synchronized void showProgressDialog(boolean isShow){

        if (mProgressDialogFragment == null) {
            return;
        }

        if (isShow) {
            if (!mProgressDialogFragment.isAdded()) {
                try {
                    mProgressDialogFragment.show(getChildFragmentManager(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (mProgressDialogFragment.isAdded()) {
                try {
                    mProgressDialogFragment.dismissAllowingStateLoss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void addFragment(int containerId, Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(containerId, fragment, fragment.getClass().getSimpleName());
        if (isAddToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commitAllowingStateLoss();
    }

    protected void replaceFragment(int containerId, Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(containerId, fragment, fragment.getClass().getSimpleName());
        if (isAddToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commitAllowingStateLoss();
    }

}