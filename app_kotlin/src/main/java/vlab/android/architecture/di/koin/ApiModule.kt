package vlab.android.architecture.di.koin

import org.koin.dsl.module
import retrofit2.Retrofit
import vlab.android.architecture.repository.source.remote.GitHubApi

@JvmField
val apiModule = module {

    // Simple Presenter Factory
    single {
        provideApi(get())
    }
}

fun provideApi(retrofit: Retrofit): GitHubApi {
    return retrofit.create(GitHubApi::class.java)
}
