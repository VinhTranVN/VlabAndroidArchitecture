package vlab.android.architecture;

import vlab.android.architecture.di.AppComponent;
import vlab.android.architecture.di.DaggerAppComponent;
import vlab.android.architecture.di.module.AppModule;
import vlab.android.architecture.di.module.NetworkModule;
import vlab.android.common.CommonApplication;
import vlab.android.common.util.LogUtils;

/**
 * Created by Vinh Tran on 2/11/18.
 */
public class MyApplication extends CommonApplication {
    private static MyApplication sInstance = null;
    private AppComponent mAppComponent;

    public static MyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        LogUtils.init(BuildConfig.DEBUG);
        LogUtils.d(getClass().getSimpleName(), ">>> MyApplication onCreate: ");

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("https://api.github.com/")) // github
                .build();

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}