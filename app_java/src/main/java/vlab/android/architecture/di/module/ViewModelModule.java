package vlab.android.architecture.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import vlab.android.architecture.di.DaggerViewModelFactory;
import vlab.android.architecture.feature.home.HomeVM;
import vlab.android.architecture.feature.login.LoginVM;
import vlab.android.architecture.feature.user_repository.UserRepositoryVM;
import vlab.android.common.di.ViewModelKey;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DaggerViewModelFactory factory);

    @Binds @IntoMap @ViewModelKey(LoginVM.class)
    abstract ViewModel bindLoginViewModel(LoginVM viewModel);

    @Binds @IntoMap @ViewModelKey(HomeVM.class)
    abstract ViewModel bindHomeViewModel(HomeVM viewModel);

    @Binds @IntoMap @ViewModelKey(UserRepositoryVM.class)
    abstract ViewModel bindUserRepositoryViewModel(UserRepositoryVM viewModel);
}
