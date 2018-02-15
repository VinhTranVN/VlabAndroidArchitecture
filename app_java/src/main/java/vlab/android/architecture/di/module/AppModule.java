package vlab.android.architecture.di.module;


import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by vinhtran on 2/11/18.
 */

@Module
public class AppModule {

    private Application mApplication;
    private Context mApplicationContext;

    public AppModule(Application application) {
        this.mApplication = application;
        this.mApplicationContext = application.getApplicationContext();
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return mApplicationContext;
    }

    @Provides
    @Singleton
    @Named("ioScheduler")
    Scheduler provideIOScheduler() {
        return Schedulers.io();
    }

}
