package vlab.android.architecture.feature.login.viewmodel;

import android.arch.lifecycle.LiveData;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseViewModel;
import vlab.android.architecture.feature.validator.TextValidator;
import vlab.android.architecture.model.UserInfo;
import vlab.android.architecture.repository.LoginRepository;
import vlab.android.common.util.LogUtils;
import vlab.android.common.util.RxCommand;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public class LoginViewModel extends BaseViewModel {

    private RxCommand<LoginRequestParam, UserInfo> mLoginCommand;
    private TextValidator mTextValidator;

    // request param
    private LoginRequestParam mLoginRequestParam = new LoginRequestParam();

    @Inject
    public LoginViewModel(LoginRepository repository, TextValidator textValidator){

        mTextValidator = textValidator;

        mLoginCommand = new RxCommand<>(mLoginRequestParam, requestParam ->
                repository.login(requestParam.userName, requestParam.pwd)
        );

        // add subscriptions
        addSubscriptions(
                mLoginCommand.subscribe()
        );
    }

    @Override
    public void onStartObservers() {
        LogUtils.println(">>> LoginViewModel -> onStartObservers : mLoginCommand.onDataChanged().hasObservers() " + mLoginCommand.onDataChanged().hasObservers());
    }

    public void login(String userName, String pwd){
        // update params
        mLoginRequestParam.userName = userName;
        mLoginRequestParam.pwd = pwd;

        if(isDataValid(mLoginRequestParam)){
            // execute command
            mLoginCommand.execute();
        }
    }

    public LiveData<UserInfo> onLoginSuccessObs() {
        return mLoginCommand.onDataChanged();
    }

    public LiveData<Throwable> onLoginErrorObs() {
        return mLoginCommand.onError();
    }

    public LiveData<Boolean> onLoadingObs() {
        return mLoginCommand.onLoading();
    }

    public boolean isDataValid(LoginRequestParam param) {
        return param != null
                && mTextValidator.isUserNameValid(param.userName)
                && mTextValidator.isPwdValid(param.pwd);
    }

    // request params
    public static class LoginRequestParam {
        String userName;
        String pwd;

        public LoginRequestParam() {}

        public LoginRequestParam(String userName, String pwd) {
            this.userName = userName;
            this.pwd = pwd;
        }
    }
}


