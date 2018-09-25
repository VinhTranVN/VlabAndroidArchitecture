package vlab.android.common.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Vinh Tran on 2/7/18.
 */
public abstract class CommonActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    protected Handler mHandler;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * add fragment into container, default add back stack
     * @param containerId
     * @param fragment
     */
    protected void addFragment(int containerId, Fragment fragment) {
        addFragment(containerId, fragment, true);
    }

    protected void addFragment(int containerId, Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(containerId, fragment, fragment.getClass().getSimpleName());
        if (isAddToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * replace fragment into container, don't add back stack
     * @param containerId
     * @param fragment
     */
    protected void replaceFragment(int containerId, Fragment fragment) {
        replaceFragment(containerId, fragment, false);
    }

    protected void replaceFragment(int containerId, Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(containerId, fragment, fragment.getClass().getSimpleName());
        if (isAddToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commitAllowingStateLoss();
    }
}