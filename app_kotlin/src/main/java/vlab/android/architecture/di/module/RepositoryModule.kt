package vlab.android.architecture.di.module

import dagger.Module
import dagger.Provides
import vlab.android.architecture.repository.LoginRepository
import vlab.android.architecture.repository.impl.LoginRepositoryImpl
import javax.inject.Singleton

/**
* Created by Vinh Tran on 2/11/18.
*/
@Module
class RepositoryModule {

    @Singleton
    @Provides
    internal fun provideLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository {
        return loginRepository
    }
}
