package vlab.android.architecture.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import vlab.android.architecture.data.source.remote.GitHubApi;

/**
 * Created by Vinh Tran on 2/11/18.
 */
@Module
public class ApiModule {

    @Singleton
    @Provides
    GitHubApi provideGitHubApi(Retrofit retrofit){
        return retrofit.create(GitHubApi.class);
    }

}
