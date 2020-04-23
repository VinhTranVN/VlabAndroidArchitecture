package vlab.android.architecture

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import vlab.android.architecture.di.koin.apiModule
import vlab.android.architecture.di.koin.networkModule
import vlab.android.architecture.di.koin.repositoryModule
import vlab.android.architecture.di.koin.viewModelModule
import vlab.android.common.util.LogUtils

/**
 * Created by Vinh Tran on 2/11/18.
 */
class MyApplication : Application() {

    companion object {
        private lateinit var sInstance: MyApplication

        fun getInstance() : MyApplication {
            return sInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        LogUtils.d(javaClass.simpleName, ">>> MyApplication onCreate")
        // init log
        LogUtils.init(BuildConfig.DEBUG)

        // start Koin context
        startKoin {
            androidContext(this@MyApplication)
            androidLogger()
            modules(
                networkModule,
                apiModule,
                repositoryModule,
                viewModelModule
            )
        }
    }

}