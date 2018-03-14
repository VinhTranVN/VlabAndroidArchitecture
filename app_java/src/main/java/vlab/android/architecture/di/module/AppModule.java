package vlab.android.architecture.di.module;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vinhtran on 2/11/18.
 */

@Module(
        includes = {
                ViewModelModule.class,
                UiModule.class,
                NetworkModule.class,
                ApiModule.class,
                RepositoryModule.class
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
