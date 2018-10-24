package vlab.android.architecture.di;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vlab.android.architecture.di.module.ApiModule;
import vlab.android.architecture.di.module.NetworkModule;
import vlab.android.architecture.di.module.RepositoryModule;
import vlab.android.architecture.di.module.UiModule;
import vlab.android.architecture.di.module.UseCaseModule;
import vlab.android.architecture.di.module.ViewModelModule;

/**
 * Created by vinhtran on 2/11/18.
 */

@Module(
        includes = {
                ViewModelModule.class,
                UiModule.class,
                NetworkModule.class,
                ApiModule.class,
                RepositoryModule.class,
                UseCaseModule.class
        }
)
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
    Context provideApplicationContext() {
        return mApplicationContext;
    }

}
