package vlab.android.common;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import vlab.android.common.di.AppInjector;

/**
 * Created by Vinh Tran on 2/11/18.
 */
public abstract class CommonApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        buildDaggerAppComponent();
        AppInjector.init(this);
    }

    protected abstract void buildDaggerAppComponent();

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}