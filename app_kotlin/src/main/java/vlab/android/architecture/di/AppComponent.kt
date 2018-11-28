package vlab.android.architecture.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import vlab.android.architecture.MyApplication
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class)
)
interface AppComponent {
    fun inject(application: MyApplication)
}
