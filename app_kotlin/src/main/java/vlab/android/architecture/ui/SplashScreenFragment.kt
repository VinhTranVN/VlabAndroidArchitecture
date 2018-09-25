package vlab.android.architecture.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.splash_screen_fragment.*
import vlab.android.architecture.R

/**
 * Created by Vinh.Tran on 8/3/18.
 **/
class SplashScreenFragment : BaseFragment() {

    override fun getLayoutRes() = R.layout.splash_screen_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_go_to_login.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_splash_to_login))

        btn_go_to_login.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.loginScreenFragment)
        }
    }

}