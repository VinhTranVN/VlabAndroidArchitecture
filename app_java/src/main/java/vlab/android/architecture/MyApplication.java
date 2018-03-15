package vlab.android.architecture;

import android.app.Activity;

import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import vlab.android.architecture.di.DaggerAppComponent;
import vlab.android.architecture.di.module.AppModule;
import vlab.android.architecture.di.module.NetworkModule;
import vlab.android.common.CommonApplication;
import vlab.android.common.di.AppInjector;
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
        LogUtils.d(getClass().getSimpleName(), ">>> MyApplication onCreate");
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        sInstance = this;
        // init log
        LogUtils.init(BuildConfig.DEBUG);

        initDependencyInjection();
    }

    private void initDependencyInjection() {
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("https://api.github.com/")) // github
                .build()
                .inject(this);
        AppInjector.init(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}