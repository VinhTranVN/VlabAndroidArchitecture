package vlab.android.architecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vlab.android.common.ui.CommonFragment
import vlab.android.common.util.LogUtils

/**
 * Created by Vinh Tran on 2/15/18.
 */

abstract class BaseFragment : CommonFragment() {

    protected lateinit var mErrorHandler: BaseErrorHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(javaClass.simpleName, ">>> onCreate")
        mErrorHandler = getErrorHandler()
    }

    protected open fun getErrorHandler(): BaseErrorHandler {
        return BaseErrorHandler()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        LogUtils.d(javaClass.simpleName, ">>> onCreateView: ")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.d(javaClass.simpleName, ">>> onViewCreated: ")

        initViewModel()

        bindViewModel()
    }

    /**
     * init view model, was called in onViewCreated()
     */
    protected abstract fun initViewModel()

    /**
     * bind data, was called in onViewCreated() after initViewModel()
     */
    protected abstract fun bindViewModel()
}
