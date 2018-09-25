package vlab.android.architecture.ui

import androidx.navigation.NavController
import androidx.navigation.Navigation
import vlab.android.architecture.R
import vlab.android.common.ui.CommonFragment

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
abstract class BaseFragment : CommonFragment() {

    protected fun getNavController(): NavController {
        return Navigation.findNavController(activity!!, R.id.navigation_host_fragment)
    }
}