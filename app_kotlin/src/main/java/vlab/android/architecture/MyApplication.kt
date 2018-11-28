package vlab.android.architecture

import vlab.android.architecture.di.AppModule
import vlab.android.architecture.di.DaggerAppComponent
import vlab.android.architecture.di.module.NetworkModule

import vlab.android.common.DaggerCommonApplication
import vlab.android.common.util.LogUtils

/**
 * Created by Vinh Tran on 2/11/18.
 */
class MyApplication : DaggerCommonApplication() {

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
    }

    override fun buildDaggerAppComponent() {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule("https://api.github.com/")) // github
                .build()
                .inject(this)
    }
}