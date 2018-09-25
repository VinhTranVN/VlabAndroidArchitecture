package vlab.android.architecture.ui

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.login_screen_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vlab.android.architecture.R
import vlab.android.architecture.data.GithubApi
import vlab.android.architecture.data.RetrofitClient
import vlab.android.architecture.data.UserResponse

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
class LoginScreenFragment : BaseDialogFragment() {

    override fun getLayoutId() = R.layout.login_screen_fragment

    private var mListener :  OnLoginFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(javaClass.name, ">>> onCreate : dialog?.isShowing " + dialog?.isShowing)

        if (activity is OnLoginFragmentListener) {
            mListener = activity as OnLoginFragmentListener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(javaClass.name, ">>> onCreateView : dialog?.isShowing " + dialog?.isShowing)
        val view = super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            val userName = edt_username.text.toString().trim()
            val pwd = edt_password.text.toString().trim()

            login(userName, pwd)
        }

        if (dialog != null) {
            btn_guest.visibility = View.GONE
        } else {
            btn_guest.setOnClickListener {
                getNavController().navigate(R.id.homeScreenFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(javaClass.name, ">>> onResume : dialog?.isShowing " + dialog?.isShowing)
    }

    private fun login(userName: String, password: String) {
        val auth = "$userName:$password";
        val authentication = "Basic " + Base64.encodeToString(auth.toByteArray(), Base64.NO_WRAP)

        val retrofit = RetrofitClient.getInstance()
        Log.d(javaClass.name, ">>> login retrofit instance @${retrofit.hashCode()}" )

        val loginService = retrofit.create(GithubApi::class.java)
        val request = loginService.login(authentication)
        request.enqueue(object : Callback<UserResponse> {

            override fun onFailure(call: Call<UserResponse>, error: Throwable) {
                //Toast.makeText(context, "onFailure " + t.message, Toast.LENGTH_SHORT).show()
                mListener?.onLoginFailed(error.message)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){

                    if (dialog != null) {
                        dismiss()
                    }

                    val userResponse = response.body()
                    mListener?.onLoginSuccess(userResponse?.name)
                } else {
                    Toast.makeText(context, "login failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    interface OnLoginFragmentListener {
        fun onLoginSuccess(userName : String?)
        fun onLoginFailed(error : String?)
    }
}