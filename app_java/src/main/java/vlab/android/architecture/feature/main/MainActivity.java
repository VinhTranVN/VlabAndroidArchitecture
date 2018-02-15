package vlab.android.architecture.feature.main;

import android.os.Bundle;

import vlab.android.architecture.R;
import vlab.android.architecture.feature.login.LoginFragment;
import vlab.android.architecture.util.LogUtils;
import vlab.android.common.ui.CommonActivity;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends CommonActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.d(getClass().getSimpleName(), ">>> onCreate: ");
        if(savedInstanceState == null){
            replaceFragment(R.id.fragment_container,
                    LoginFragment.instantiate(this, LoginFragment.class.getName()),
                    false);
        }
    }
}

