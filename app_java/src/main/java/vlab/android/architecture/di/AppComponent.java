package vlab.android.architecture.di;

import javax.inject.Singleton;

import dagger.Component;
import vlab.android.architecture.di.module.ApiModule;
import vlab.android.architecture.di.module.AppModule;
import vlab.android.architecture.di.module.NetworkModule;
import vlab.android.architecture.di.module.RepositoryModule;
import vlab.android.architecture.feature.login.LoginFragment;

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class,
                ApiModule.class,
                RepositoryModule.class
        }
)
public interface AppComponent {

    void inject(LoginFragment baseFragment);
}

