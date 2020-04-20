package vlab.android.architecture.di


import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import vlab.android.architecture.di.module.ApiModule
import vlab.android.architecture.di.module.NetworkModule
import vlab.android.architecture.di.module.RepositoryModule
import vlab.android.architecture.di.module.UiModule
import vlab.android.architecture.di.module.ViewModelModule
import javax.inject.Singleton

/**
 * Created by vinhtran on 2/11/18.
 */

@Module(includes = arrayOf(
        ViewModelModule::class,
        UiModule::class,
        NetworkModule::class,
        ApiModule::class,
        RepositoryModule::class)
)
class AppModule {

    private val mApplication: Application

    constructor(mApplication: Application) {
        this.mApplication = mApplication
        this.mApplicationContext = mApplication.applicationContext
    }

    private val mApplicationContext: Context

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return mApplicationContext
    }

}
