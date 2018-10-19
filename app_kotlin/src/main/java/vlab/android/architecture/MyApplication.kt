package vlab.android.architecture

import android.app.Application

import vlab.android.common.util.LogUtils

/**
 * Created by Vinh Tran on 2/11/18.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        LogUtils.d(javaClass.simpleName, ">>> MyApplication onCreate")
        instance = this

        // init log
        LogUtils.init(BuildConfig.DEBUG)
    }

    companion object {
        @JvmStatic
        var instance: MyApplication? = null
            private set
    }
}