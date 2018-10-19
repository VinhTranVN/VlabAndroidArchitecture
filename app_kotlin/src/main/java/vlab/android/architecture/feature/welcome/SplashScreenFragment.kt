package vlab.android.architecture.feature.welcome

import vlab.android.architecture.R
import vlab.android.architecture.base.BaseFragment

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
class SplashScreenFragment : BaseFragment() {

    override fun getLayoutRes() = R.layout.splash_screen_fragment

    override fun onStart() {
        super.onStart()
        mHandler.postDelayed({
            /*val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.nav_graph, true)
                    .build()*/

            getNavController().navigate(R.id.action_splash_to_login)

        }, 2000)
    }

}