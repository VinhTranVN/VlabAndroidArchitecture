package vlab.android.architecture.di.module;

import dagger.Module;
import dagger.Provides;
import vlab.android.architecture.feature.login.usecase.LoginUseCase;
import vlab.android.architecture.feature.user_repository.UserRepositoryUseCase;
import vlab.android.architecture.repository.GithubRepository;
import vlab.android.architecture.repository.LoginRepository;

/**
 * Created by Vinh Tran on 10/24/18.
 */
@Module
public class UseCaseModule {

    @Provides
    LoginUseCase provideLoginUC(LoginRepository repository){
        return new LoginUseCase(repository);
    }

    @Provides
    UserRepositoryUseCase provideRepositoryUC(GithubRepository repository){
        return new UserRepositoryUseCase(repository);
    }
}
