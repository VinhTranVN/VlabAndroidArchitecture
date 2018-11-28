package vlab.android.architecture.base

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vlab.android.common.ui.CommonFragment
import vlab.android.common.util.LogUtils
import javax.inject.Inject

/**
 * Created by Vinh Tran on 2/15/18.
 */

abstract class BaseFragment : CommonFragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

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
     * provide ViewModel for this fragment
     * @param vmClass
     * @return ViewModel for this fragment
     */
    protected fun <T : BaseViewModel> provideViewModel(vmClass: Class<T>): T {
        return provideViewModel(vmClass, false)
    }

    /**
     * provide ViewModel for this fragment
     * @param vmClass
     * @param isShareSameActivity false for default for this fragment,
     * true for share this VM with other fragment belong the same Activity
     * @return ViewModel for this fragment
     */
    protected fun <T : BaseViewModel> provideViewModel(vmClass: Class<T>, isShareSameActivity: Boolean): T {
        // in case we want to share ViewMode with other fragments belong the activity
        val viewModel: T
        if (isShareSameActivity) {
            viewModel = ViewModelProviders.of(activity!!, mViewModelFactory).get(vmClass)
        } else {
            viewModel = ViewModelProviders.of(this, mViewModelFactory).get(vmClass)
        }

        viewModel.setLifeCycleOwner(this)

        return viewModel
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
