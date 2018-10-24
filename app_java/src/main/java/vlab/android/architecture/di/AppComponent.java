package vlab.android.architecture.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import vlab.android.architecture.MyApplication;

@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                AppModule.class
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

