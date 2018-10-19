package vlab.android.architecture.base

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModel
import android.content.Context

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import vlab.android.architecture.MyApplication


/**
 * Created by Vinh Tran on 2/15/18.
 */

open class BaseViewModel : ViewModel() {

    private var mCompositeDisposable = CompositeDisposable()
    var mLifeCycleOwner: LifecycleOwner? = null

    val appContext: Context
        get() = MyApplication.instance!!.applicationContext

    protected fun addSubscriptions(vararg disposables: Disposable) {
        mCompositeDisposable.addAll(*disposables)
    }

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.clear()
        mLifeCycleOwner = null
    }
}
