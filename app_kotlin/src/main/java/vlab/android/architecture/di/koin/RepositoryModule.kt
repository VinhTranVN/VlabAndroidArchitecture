package vlab.android.architecture.di.koin

import org.koin.dsl.module
import vlab.android.architecture.repository.LoginRepository
import vlab.android.architecture.repository.impl.LoginRepositoryImpl

/**
 * Created by Vinh Tran on 2/11/18.
 */

@JvmField
val repositoryModule = module {

    // Simple Presenter Factory
    single<LoginRepository> { LoginRepositoryImpl(get()) }
}
