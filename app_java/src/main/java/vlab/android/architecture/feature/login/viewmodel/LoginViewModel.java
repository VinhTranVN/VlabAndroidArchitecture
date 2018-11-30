package vlab.android.architecture.feature.login.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseViewModel;
import vlab.android.architecture.feature.login.model.UserInfo;
import vlab.android.architecture.feature.login.usecase.LoginUseCase;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public class LoginViewModel extends BaseViewModel {

    private LoginUseCase mLoginUseCase;

    @Inject
    public LoginViewModel(LoginUseCase loginUseCase) {
        mLoginUseCase = loginUseCase;
    }

    @Override
    protected void onCleared() {
        mLoginUseCase.onCleared();
        super.onCleared();
    }

    public void login(String userName, String pwd) {
        // map request data
        LoginUseCase.LoginRequestParam requestData = new LoginUseCase.LoginRequestParam(userName, pwd);

        mLoginUseCase.login(requestData);
    }

    public void cancelLogin(){
        mLoginUseCase.cancelLoginRequest();
    }

    public LiveData<UserInfo> onLoginSuccessObs() {
        return Transformations.map(mLoginUseCase.onLoginSuccessObs(), UserInfo::new);
    }

    public LiveData<Throwable> onLoginErrorObs() {
        return mLoginUseCase.onLoginErrorObs();
    }

    public LiveData<Boolean> onLoadingObs() {
        return mLoginUseCase.onLoadingObs();
    }
}


