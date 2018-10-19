package vlab.android.architecture.base

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import vlab.android.architecture.R

import vlab.android.common.ui.CommonFragment
import vlab.android.common.util.LogUtils

/**
 * Created by Vinh Tran on 2/15/18.
 */

abstract class BaseFragment : CommonFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(javaClass.simpleName, ">>> onCreate")
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        LogUtils.d(javaClass.simpleName, ">>> onCreateView: ")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
    }

    protected fun getNavController(): NavController {
        return Navigation.findNavController(activity!!, R.id.navigation_host_fragment)
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
            viewModel = ViewModelProviders.of(activity!!).get(vmClass)
        } else {
            viewModel = ViewModelProviders.of(this).get(vmClass)
        }

        viewModel.mLifeCycleOwner = this

        return viewModel
    }

    /**
     * init view model
     */
    protected open fun initViewModel() {

    }

    /**
     * bind data
     */
    protected open fun bindViewModel() {

    }
}
