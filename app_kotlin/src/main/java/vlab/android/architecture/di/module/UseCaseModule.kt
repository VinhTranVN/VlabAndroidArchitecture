package vlab.android.architecture.di.module

import dagger.Module
import dagger.Provides
import vlab.android.architecture.feature.login.use_case.LoginUseCase
import vlab.android.architecture.repository.LoginRepository

/**
 * Created by Vinh Tran on 10/24/18.
 */
@Module
class UseCaseModule {

    @Provides
    internal fun provideLoginUC(repository: LoginRepository): LoginUseCase {
        return LoginUseCase(repository)
    }
}
