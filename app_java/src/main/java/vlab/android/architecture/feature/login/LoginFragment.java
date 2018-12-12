package vlab.android.architecture.feature.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import vlab.android.architecture.R;
import vlab.android.architecture.base.BaseErrorHandler;
import vlab.android.architecture.base.BaseFragment;
import vlab.android.architecture.feature.login.model.UserModel;
import vlab.android.common.util.LogUtils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginFragment extends BaseFragment {

    private TextView mTvResult;
    // view model for login
    private LoginViewModel mViewModel;
    private View mProgressView;

    private OnLoginFragmentListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginFragmentListener) {
            mListener = (OnLoginFragmentListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected BaseErrorHandler getErrorHandler() {
        return new LoginErrorHandler();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mViewModel.isLoggedIn()){
            if (mListener != null) {
                mListener.onLoginSuccess(mViewModel.getLoggedInUserInfo());
                return;
            }
        }

        AutoCompleteTextView email = view.findViewById(R.id.email);
        EditText password = view.findViewById(R.id.password);
        mTvResult = view.findViewById(R.id.tv_result);
        mProgressView = view.findViewById(R.id.progressBar);

        view.findViewById(R.id.btn_guest).setOnClickListener(view1 -> {
            if (mListener != null) {
                mListener.onLoginAsGuest();
            }
        });

        view.findViewById(R.id.btn_sign_in).setOnClickListener(view1 -> {
            mViewModel.login(email.getText().toString(), password.getText().toString());
        });

        view.findViewById(R.id.btn_cancel_sign_in).setOnClickListener(view1 -> {
            mViewModel.cancelLogin();
        });
    }

    @Override
    protected void initViewModel() {
        mViewModel = provideViewModel(LoginViewModel.class);
    }

    @Override
    protected void bindViewModel() {

        mViewModel.onLoginSuccessObs().observe(this, userModel -> {
            LogUtils.println(">>> LoginFragment -> onLoginSuccessObs : " + userModel.getUserName());
            mTvResult.setText(getString(R.string.login_success, userModel.getUserName()));
            if (mListener != null) {
                mListener.onLoginSuccess(userModel);
            }
        });

        mViewModel.onLoginErrorObs().observe(this, error -> mTvResult.setText(mErrorHandler.parseError(error)));

        mViewModel.onLoadingObs().observe(this, isShow -> {
            mProgressView.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
        });
    }

    public void setOnLoginFragmentListener(OnLoginFragmentListener fragmentListener) {
        mListener = fragmentListener;
    }

    public interface OnLoginFragmentListener {
        void onLoginAsGuest();
        void onLoginSuccess(UserModel userModel);
    }
}

