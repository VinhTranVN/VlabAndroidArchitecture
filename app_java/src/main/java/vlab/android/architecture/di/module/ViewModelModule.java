package vlab.android.architecture.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import vlab.android.architecture.di.DaggerViewModelFactory;
import vlab.android.architecture.feature.home.viewmodel.HomeViewModel;
import vlab.android.architecture.feature.login.viewmodel.LoginViewModel;
import vlab.android.common.di.ViewModelKey;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);

    @Binds @IntoMap @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);

    @Binds @IntoMap @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel viewModel);
}
