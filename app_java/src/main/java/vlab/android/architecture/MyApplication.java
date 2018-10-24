package vlab.android.architecture;

import com.squareup.leakcanary.LeakCanary;

import vlab.android.architecture.di.AppModule;
import vlab.android.architecture.di.DaggerAppComponent;
import vlab.android.architecture.di.module.NetworkModule;
import vlab.android.common.DaggerCommonApplication;
import vlab.android.common.util.LogUtils;

/**
 * Created by Vinh Tran on 2/11/18.
 */
public class MyApplication extends DaggerCommonApplication {
    private static MyApplication sInstance = null;

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
        LogUtils.init(vlab.android.common.BuildConfig.DEBUG);
    }

    @Override
    protected void buildDaggerAppComponent() {
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("https://api.github.com/")) // github
                .build()
                .inject(this);
    }
}