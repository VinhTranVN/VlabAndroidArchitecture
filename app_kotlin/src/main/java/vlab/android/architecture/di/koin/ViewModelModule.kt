package vlab.android.architecture.di.koin

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import vlab.android.architecture.feature.home.viewmodel.HomeViewModel
import vlab.android.architecture.feature.login.use_case.LoginUseCase
import vlab.android.architecture.feature.login.viewmodel.LoginViewModel

@JvmField
val viewModelModule = module {

    viewModel { HomeViewModel() }

    viewModel { LoginViewModel(LoginUseCase(get())) }

}