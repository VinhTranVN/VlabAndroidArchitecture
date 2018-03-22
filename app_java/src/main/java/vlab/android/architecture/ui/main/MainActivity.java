package vlab.android.architecture.ui.main;

import android.os.Bundle;

import vlab.android.architecture.R;
import vlab.android.architecture.base.BaseActivity;
import vlab.android.architecture.ui.login.LoginFragment;
import vlab.android.common.util.LogUtils;

/**
 * A login screen that offers login via email/password.
 */
public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtils.d(getClass().getSimpleName(), ">>> onCreate: " + Thread.currentThread());
        if(savedInstanceState == null){
            replaceFragment(R.id.fragment_container,
                    LoginFragment.instantiate(this, LoginFragment.class.getName()),
                    false);
        }
    }
}

