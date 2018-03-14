package vlab.android.architecture.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import vlab.android.architecture.R;
import vlab.android.architecture.base.BaseFragment;

/**
 * A login screen that offers login via email/password.
 */
public class LoginFragment extends BaseFragment<LoginViewModel> {

    private TextView mTvResult;
    private LoginErrorHandle mLoginErrorHandle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginErrorHandle = new LoginErrorHandle(getContext());
    }

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
        mViewModel.onLoginSuccessObs().observe(this, userInfo -> {
            mTvResult.setText(getString(R.string.login_success, userInfo.getUserName()));
        });

        mViewModel.onLoginFailedObs().observe(this, throwable -> {
            mTvResult.setText(mLoginErrorHandle.parseError(throwable));
        });

        mViewModel.onLoadingObs().observe(this, this::showProgressDialog);
    }
}

