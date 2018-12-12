package vlab.android.architecture.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vlab.android.architecture.repository.GithubRepository;
import vlab.android.architecture.repository.LoginRepository;
import vlab.android.architecture.repository.SessionRepository;
import vlab.android.architecture.repository.impl.GithubRepositoryImpl;
import vlab.android.architecture.repository.impl.LoginRepositoryImpl;
import vlab.android.architecture.repository.impl.SessionRepositoryImpl;

/**
 * Created by Vinh Tran on 2/11/18.
 */
@Module
public class RepositoryModule {

    @Provides
    LoginRepository provideLoginRepository(LoginRepositoryImpl repository) {
        return repository;
    }

    @Singleton
    @Provides
    SessionRepository provideSessionRepository(SessionRepositoryImpl repository) {
        return repository;
    }

    @Provides
    GithubRepository provideGithubRepository(GithubRepositoryImpl repository) {
        return repository;
    }
}
