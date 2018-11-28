package vlab.android.architecture.feature.main

import android.os.Bundle

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import vlab.android.architecture.R
import vlab.android.architecture.base.BaseActivity
import vlab.android.architecture.feature.home.HomeFragment
import vlab.android.architecture.feature.login.LoginFragment

/**
 * A login screen that offers login via email/password.
 */
class MainActivity : BaseActivity(), LoginFragment.OnLoginFragmentListener, HomeFragment.OnHomeFragmentListener {

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.host_fragment)
    }

    override fun onHomeFragmentCallback() {

    }

    override fun onLoginAsGuest() {
        navController!!.navigate(R.id.homeFragment)
    }

    override fun onLoginSuccess(userName: String?) {
        val args = Bundle()
        args.putString(HomeFragment.ARG_USER_NAME, userName)

        val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.navigation_graph, true)
                .build()

        navController!!.navigate(R.id.homeFragment, args
                //, navOptions
        )
    }
}

