package vlab.android.architecture.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import vlab.android.architecture.feature.login.LoginFragment;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract LoginFragment contributeLoginFragment();

}
