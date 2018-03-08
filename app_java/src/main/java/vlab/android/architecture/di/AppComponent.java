package vlab.android.architecture.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import vlab.android.architecture.MyApplication;
import vlab.android.architecture.di.module.ActivityModule;
import vlab.android.architecture.di.module.ApiModule;
import vlab.android.architecture.di.module.AppModule;
import vlab.android.architecture.di.module.NetworkModule;
import vlab.android.architecture.di.module.RepositoryModule;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                ActivityModule.class,
                AppModule.class,
                NetworkModule.class,
                ApiModule.class,
                RepositoryModule.class
        }
)
public interface AppComponent {

//    @Component.Builder
//    interface Builder {
//        @BindsInstance Builder application(Application application);
//        @BindsInstance Builder appModule(AppModule appModule);
//        //@BindsInstance Builder networkModule(NetworkModule networkModule);
//        AppComponent build();
//    }

    void inject(MyApplication application);
}

