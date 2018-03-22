package vlab.android.common.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vlab.android.common.di.Injectable;
import vlab.android.common.util.LogUtils;

/**
* Created by Vinh Tran on 2/7/18.
*/
public abstract class CommonFragment extends Fragment implements Injectable {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    /**
     * declare layout resource id
     */
    protected abstract int getLayoutRes();

    public synchronized void showProgressDialog(boolean isShow){
        LogUtils.d(getClass().getSimpleName(), ">>> showProgressDialog " + isShow);
        try {
            CommonProgressDialogFragment progressDialog = (CommonProgressDialogFragment) getChildFragmentManager().findFragmentByTag("ProgressDialogFragment");

            if (progressDialog == null) {
                progressDialog = CommonProgressDialogFragment.newInstance(null, 1);
            }

            if (isShow) {
                if (!progressDialog.isAdded()) {
                    progressDialog.show(getChildFragmentManager(), "ProgressDialogFragment");
                }
            } else {
                if (progressDialog.isAdded()) {
                    progressDialog.dismissAllowingStateLoss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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