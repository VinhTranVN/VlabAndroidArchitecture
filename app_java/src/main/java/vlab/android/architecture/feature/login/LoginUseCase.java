package vlab.android.architecture.feature.login;

import android.arch.lifecycle.LiveData;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseUseCase;
import vlab.android.architecture.feature.user.UserModel;
import vlab.android.architecture.repository.LoginRepository;
import vlab.android.architecture.repository.SessionRepository;
import vlab.android.architecture.repository.source.remote.response.UserResponse;
import vlab.android.architecture.util.TextValidator;
import vlab.android.common.util.RxTask;

/**
 * Created by Vinh.Tran on 10/25/18.
 **/
public class LoginUseCase extends BaseUseCase {

    private RxTask<LoginUseCase.LoginRequestParam, UserResponse> mLoginTask;
    private SessionRepository mSessionRepository;

    @Inject
    public LoginUseCase(LoginRepository repository, SessionRepository sessionRepository){

        mSessionRepository = sessionRepository;
        mLoginTask = new RxTask<>(requestData -> repository.login(requestData.mUserName, requestData.mPwd));
    }

    @Override
    public void onCleared() {
        mLoginTask.destroy();
        super.onCleared();
    }

    public void login(LoginUseCase.LoginRequestParam loginRequestParam){
        // update params
        if(isDataValid(loginRequestParam)){
            // execute command
            mLoginTask.execute(loginRequestParam);
        }
    }

    public void cancelLoginRequest(){
        mLoginTask.cancel();
    }

    public LiveData<UserResponse> onLoginSuccessObs() {
        return mLoginTask.onSingleLiveDataChanged();
    }

    public LiveData<Throwable> onLoginErrorObs() {
        return mLoginTask.onError();
    }

    public LiveData<Boolean> onLoadingObs() {
        return mLoginTask.onLoading();
    }

    public boolean isDataValid(LoginUseCase.LoginRequestParam param) {
        return param != null
                && TextValidator.isUserNameValid(param.mUserName)
                && TextValidator.isPwdValid(param.mPwd);
    }

    public boolean isLoggedIn() {
        return mSessionRepository.isLoggedIn();
    }

    public UserModel getLoggedUserInfo() {
        return mSessionRepository.getUserSession();
    }

    // request params
    public static class LoginRequestParam {
        String mUserName;
        String mPwd;

        public LoginRequestParam() {}

        public LoginRequestParam(String userName, String pwd) {
            this.mUserName = userName;
            this.mPwd = pwd;
        }
    }
}