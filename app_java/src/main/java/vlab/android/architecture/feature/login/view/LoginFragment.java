package vlab.android.architecture.feature.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import vlab.android.architecture.R;
import vlab.android.architecture.base.BaseErrorHandler;
import vlab.android.architecture.base.BaseFragment;
import vlab.android.architecture.feature.login.LoginErrorHandler;
import vlab.android.architecture.feature.login.viewmodel.LoginViewModel;

/**
 * A login screen that offers login via email/password.
 */
public class LoginFragment extends BaseFragment {

    private TextView mTvResult;
    // view model for login
    private LoginViewModel mViewModel;

    @Override
    protected BaseErrorHandler getErrorHandler() {
        return new LoginErrorHandler();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AutoCompleteTextView email = view.findViewById(R.id.email);
        EditText password = view.findViewById(R.id.password);
        mTvResult = view.findViewById(R.id.tv_result);

        view.findViewById(R.id.btn_sign_in).setOnClickListener(view1 -> {
            mViewModel.login(email.getText().toString(), password.getText().toString());
        });
    }

    @Override
    protected void initViewModel() {
        mViewModel = provideViewModel(LoginViewModel.class);
    }

    @Override
    protected void bindViewModel() {

        mViewModel.onLoginSuccessObs().observe(this, userModel -> {
            mTvResult.setText(getString(R.string.login_success, userModel.getUserName()));
        });

        mViewModel.onLoginErrorObs().observe(this, error -> {
            mTvResult.setText(mErrorHandler.parseError(error));
        });

        mViewModel.onLoadingObs().observe(this, this::showProgressDialog);
    }
}

