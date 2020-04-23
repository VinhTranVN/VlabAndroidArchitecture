package vlab.android.architecture.feature.login

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import org.koin.android.viewmodel.ext.android.viewModel
import vlab.android.architecture.R
import vlab.android.architecture.base.BaseErrorHandler
import vlab.android.architecture.base.BaseFragment
import vlab.android.architecture.feature.login.viewmodel.LoginViewModel

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
class LoginFragment : BaseFragment() {

    private var mTvResult: TextView? = null
    // view model for login
    private val mViewModel by viewModel<LoginViewModel>()
    private var mProgressView: View? = null

    private var mListener: OnLoginFragmentListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnLoginFragmentListener) {
            mListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun getErrorHandler(): BaseErrorHandler {
        return LoginErrorHandler()
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = view.findViewById<AutoCompleteTextView>(R.id.email)
        val password = view.findViewById<EditText>(R.id.password)
        mTvResult = view.findViewById(R.id.tv_result)
        mProgressView = view.findViewById(R.id.progressBar)

        view.findViewById<View>(R.id.btn_guest).setOnClickListener { view1 ->
            mListener?.onLoginAsGuest()
        }

        view.findViewById<View>(R.id.btn_sign_in).setOnClickListener { view1 -> mViewModel.login(email.text.toString(), password.text.toString()) }

        view.findViewById<View>(R.id.btn_cancel_sign_in).setOnClickListener { view1 -> mViewModel.cancelLogin() }
    }

    override fun initViewModel() {
        // TODO
    }

    override fun bindViewModel() {

        mViewModel.onLoginSuccessObs().observe(this, Observer { userModel ->
            //LogUtils.println(">>> LoginFragment -> onLoginSuccessObs")
            mTvResult!!.text = getString(R.string.login_success, userModel?.userName)
            mListener?.onLoginSuccess(userModel?.userName)
        })

        mViewModel.onLoginErrorObs().observe(this,
                Observer { error ->
                    mTvResult!!.text = mErrorHandler.parseError(error!!)
                }
        )

        mViewModel.onLoadingObs().observe(this,
                Observer { isShow ->
                    mProgressView?.visibility = if (isShow!!) View.VISIBLE else View.INVISIBLE
                }
        )
    }

    fun setOnLoginFragmentListener(fragmentListener: OnLoginFragmentListener) {
        mListener = fragmentListener
    }

    interface OnLoginFragmentListener {
        fun onLoginAsGuest()
        fun onLoginSuccess(userName: String?)
    }
}