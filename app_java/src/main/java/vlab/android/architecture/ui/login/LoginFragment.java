package vlab.android.architecture.ui.login;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vlab.android.architecture.R;
import vlab.android.architecture.base.BaseFragment;
import vlab.android.common.util.LogUtils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginFragment extends BaseFragment<LoginViewModel> {

    private TextView mTvResult;

    @Override
    protected Class<LoginViewModel> getViewModelClass() {
        return LoginViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View rootView) {

        AutoCompleteTextView email = rootView.findViewById(R.id.email);
        EditText password = rootView.findViewById(R.id.password);
        mTvResult = rootView.findViewById(R.id.tv_result);

        rootView.findViewById(R.id.btn_sign_in).setOnClickListener(view -> {
            mViewModel.login(email.getText().toString(), password.getText().toString());
        });
    }

    @Override
    protected void bindViewModel() {
        mViewModel.onLoginSuccessObs().observe(this, userInfoResponse -> {
            if (userInfoResponse.getError() != null) {
                Toast.makeText(LoginFragment.this.getContext(), "Login Failed : " + userInfoResponse.getError().getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                String welcomeMsg = "Welcome : " + userInfoResponse.getData().getUserName();
                LogUtils.d(LoginFragment.this.getClass().getSimpleName(), ">>> onLoginSuccessObs: " + welcomeMsg);
                mTvResult.setText(welcomeMsg);
            }
        });

        mViewModel.onLoadingObs().observe(this, this::showProgressDialog);
    }
}

