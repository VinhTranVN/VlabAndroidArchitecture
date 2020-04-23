package vlab.android.architecture.feature.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.koin.android.viewmodel.ext.android.viewModel

import vlab.android.architecture.R
import vlab.android.architecture.base.BaseFragment
import vlab.android.architecture.feature.home.viewmodel.HomeViewModel

class HomeFragment : BaseFragment() {
    private var mListener: OnHomeFragmentListener? = null

    val mViewModel: HomeViewModel by viewModel()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnHomeFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun getLayoutRes() = R.layout.fragment_home

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun initViewModel() {
        if (arguments != null) {
            // TODO map argument to ViewModel
            val userName = arguments!!.getString(ARG_USER_NAME)
            mViewModel!!.userName = userName
        }
    }

    override fun bindViewModel() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (view.findViewById<View>(R.id.tv_welcome_msg) as TextView).text = mViewModel!!.userName
    }

    fun setOnHomeFragmentListener(listener: OnHomeFragmentListener) {
        mListener = listener
    }

    interface OnHomeFragmentListener {
        // TODO: Update argument type and name
        fun onHomeFragmentCallback()
    }

    companion object {

        val ARG_USER_NAME = "ARG_USER_NAME"

        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
