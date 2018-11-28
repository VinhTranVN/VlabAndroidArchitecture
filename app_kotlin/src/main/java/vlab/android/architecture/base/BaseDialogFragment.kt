package vlab.android.architecture.base

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import vlab.android.architecture.R

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
abstract class BaseDialogFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract fun getLayoutId() : Int

    protected fun getNavController(): NavController {
        return Navigation.findNavController(activity!!, R.id.host_fragment)
    }
}