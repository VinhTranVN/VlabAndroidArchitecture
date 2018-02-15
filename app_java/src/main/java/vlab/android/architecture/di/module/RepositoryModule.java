package vlab.android.architecture.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vlab.android.architecture.data.repository.LoginRepository;
import vlab.android.architecture.data.repository.impl.LoginRepositoryImpl;

/**
 * Created by Vinh Tran on 2/11/18.
 */
@Module
public class RepositoryModule {

    @Singleton
    @Provides
    LoginRepository provideLoginRepository(LoginRepositoryImpl loginRepository) {
        return loginRepository;
    }
}
