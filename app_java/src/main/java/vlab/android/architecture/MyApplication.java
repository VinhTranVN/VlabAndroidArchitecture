package vlab.android.architecture;

import android.app.Activity;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import vlab.android.architecture.di.module.AppInjector;
import vlab.android.common.CommonApplication;
import vlab.android.common.util.LogUtils;

/**
 * Created by Vinh Tran on 2/11/18.
 */
public class MyApplication extends CommonApplication implements HasActivityInjector {
    private static MyApplication sInstance = null;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public static MyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        LogUtils.init(BuildConfig.DEBUG);
        LogUtils.d(getClass().getSimpleName(), ">>> MyApplication onCreate: ");
        AppInjector.init(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}