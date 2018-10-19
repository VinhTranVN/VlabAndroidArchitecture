package vlab.android.architecture.feature

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import vlab.android.architecture.R
import vlab.android.architecture.feature.login.LoginScreenFragment
import vlab.android.common.ui.CommonActivity


class MainActivity : CommonActivity(), LoginScreenFragment.OnLoginFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val hostFragment = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction()
                .replace(R.id.navigation_host_fragment, hostFragment)
                .setPrimaryNavigationFragment(hostFragment) // this is the equivalent to app:defaultNavHost="true"
                .commit()*/
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.navigation_host_fragment).navigateUp()
    }

    override fun onLoginSuccess(userName: String?) {
        val arg = Bundle()
        arg.putString("name", userName)
        getNavController().navigate(R.id.homeScreenFragment, arg, NavOptions.Builder().setPopUpTo(R.id.nav_graph, true).build())
    }

    override fun onLoginFailed(error: String?) {
        Toast.makeText(this, ">>> onLoginFailed $error", Toast.LENGTH_SHORT).show()
    }

    protected fun getNavController(): NavController {
        return Navigation.findNavController(this, R.id.navigation_host_fragment)
    }
}
