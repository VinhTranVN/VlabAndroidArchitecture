package vlab.android.architecture.feature.main;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import vlab.android.architecture.R;
import vlab.android.architecture.base.BaseActivity;
import vlab.android.architecture.feature.home.HomeFragment;
import vlab.android.architecture.feature.login.LoginFragment;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends BaseActivity implements
        LoginFragment.OnLoginFragmentListener,
        HomeFragment.OnHomeFragmentListener {

    private NavController mNavController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavController = Navigation.findNavController(this, R.id.host_fragment);
    }

    private NavController getNavController() {
        return mNavController;
    }

    @Override
    public void onHomeFragmentCallback() {

    }

    @Override
    public void onLoginAsGuest() {
        getNavController().navigate(R.id.homeFragment);
    }

    @Override
    public void onLoginSuccess(String userName) {
        Bundle args = new Bundle();
        args.putString(HomeFragment.ARG_USER_NAME, userName);
        getNavController().navigate(R.id.homeFragment, args,
                new NavOptions.Builder()
                        .setPopUpTo(R.id.navigation_graph, true)
                        .build());
    }
}

