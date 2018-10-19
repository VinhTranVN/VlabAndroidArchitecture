package vlab.android.architecture.feature.home

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.home_screen_fragment.*
import vlab.android.architecture.R
import vlab.android.architecture.base.BaseFragment
import vlab.android.architecture.feature.login.LoginScreenFragment

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
class HomeScreenFragment : BaseFragment() {
    override fun getLayoutRes() = R.layout.home_screen_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val welcomeMsg = arguments?.getString("name", "Please Login")

        tv_welcome.text = welcomeMsg
        btn_login.visibility = if (welcomeMsg.equals("Please Login")) View.VISIBLE else View.GONE

        btn_login.setOnClickListener {
            LoginScreenFragment().show(activity?.supportFragmentManager, "login_dialog")
        }

        btn_friends.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.friendListFragment)
        }
    }

    override fun initViewModel() {

    }

    override fun bindViewModel() {

    }
}