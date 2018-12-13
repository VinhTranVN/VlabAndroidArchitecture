package vlab.android.architecture.feature.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseViewModel;
import vlab.android.architecture.feature.user.UserModel;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public class LoginVM extends BaseViewModel {

    private LoginUseCase mLoginUseCase;

    @Inject
    public LoginVM(LoginUseCase loginUseCase) {
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

    public LiveData<UserModel> onLoginSuccessObs() {
        // TODO check Transformations.map swallow SingleLiveData behavior
        return Transformations.map(mLoginUseCase.onLoginSuccessObs(), UserModel::new);
    }

    public LiveData<Throwable> onLoginErrorObs() {
        return mLoginUseCase.onLoginErrorObs();
    }

    public LiveData<Boolean> onLoadingObs() {
        return mLoginUseCase.onLoadingObs();
    }

    public boolean isLoggedIn() {
        return mLoginUseCase.isLoggedIn();
    }

    public UserModel getLoggedInUserInfo() {
        return mLoginUseCase.getLoggedUserInfo();
    }
}


