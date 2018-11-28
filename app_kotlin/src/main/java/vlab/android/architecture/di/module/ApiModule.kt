package vlab.android.architecture.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import vlab.android.architecture.repository.source.remote.GitHubApi
import javax.inject.Singleton

/**
 * Created by vinhtran on 2/11/18.
 */
@Module
class ApiModule {

    @Singleton
    @Provides
    internal fun provideGitHubApi(retrofit: Retrofit): GitHubApi {
        return retrofit.create(GitHubApi::class.java)
    }

}
