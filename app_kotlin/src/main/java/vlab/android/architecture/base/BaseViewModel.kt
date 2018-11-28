package vlab.android.architecture.base

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModel
import android.content.Context

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import vlab.android.architecture.MyApplication
import vlab.android.common.util.LogUtils


/**
 * Created by Vinh Tran on 2/15/18.
 */

open class BaseViewModel : ViewModel() {

    protected var mCompositeDisposable = CompositeDisposable()
    protected var mLifeCycleOwner: LifecycleOwner? = null

    protected fun addSubscriptions(vararg disposables: Disposable) {
        mCompositeDisposable.addAll(*disposables)
    }

    protected fun BaseViewModel() {

    }

    override fun onCleared() {
        LogUtils.d(javaClass.simpleName, ">>> onCleared: ")
        mCompositeDisposable.clear()
        mLifeCycleOwner = null
        super.onCleared()
    }

    fun getAppContext(): Context {
        return MyApplication.getInstance().getApplicationContext()
    }

    fun setLifeCycleOwner(lifeCycleOwner: LifecycleOwner) {
        mLifeCycleOwner = lifeCycleOwner
    }

    fun getLifeCycleOwner(): LifecycleOwner? {
        return mLifeCycleOwner
    }
}
