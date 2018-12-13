package vlab.android.architecture.di.module;

import dagger.Module;
import dagger.Provides;
import vlab.android.architecture.feature.login.LoginUseCase;
import vlab.android.architecture.feature.user_repository.UserRepositoryUseCase;
import vlab.android.architecture.repository.GithubRepository;
import vlab.android.architecture.repository.LoginRepository;
import vlab.android.architecture.repository.SessionRepository;

/**
 * Created by Vinh Tran on 10/24/18.
 */
@Module
public class UseCaseModule {

    @Provides
    LoginUseCase provideLoginUC(LoginRepository repository, SessionRepository sessionRepository){
        return new LoginUseCase(repository, sessionRepository);
    }

    @Provides
    UserRepositoryUseCase provideRepositoryUC(GithubRepository githubRepository, SessionRepository sessionRepository){
        return new UserRepositoryUseCase(githubRepository, sessionRepository);
    }
}
