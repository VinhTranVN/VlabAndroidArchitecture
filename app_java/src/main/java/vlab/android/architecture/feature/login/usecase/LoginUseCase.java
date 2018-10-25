package vlab.android.architecture.feature.login.usecase;

import android.arch.lifecycle.LiveData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import vlab.android.architecture.base.IUseCase;
import vlab.android.architecture.feature.validator.TextValidator;
import vlab.android.architecture.model.UserInfo;
import vlab.android.architecture.repository.LoginRepository;
import vlab.android.common.util.RxTask;

/**
 * Created by Vinh.Tran on 10/25/18.
 **/
public class LoginUseCase implements IUseCase {

    private RxTask<LoginUseCase.LoginRequestParam, UserInfo> mLoginTask;
    private TextValidator mTextValidator;

    @Inject
    public LoginUseCase(LoginRepository repository, TextValidator textValidator){

        mTextValidator = textValidator;

        mLoginTask = new RxTask<>(requestData -> repository.login(requestData.mUserName, requestData.mPwd));
    }

    @Override
    public Disposable[] subscribes() {
        return new Disposable[]{
                mLoginTask.subscribe()
        };
    }

    public void login(LoginUseCase.LoginRequestParam loginRequestParam){
        // update params
        if(isDataValid(loginRequestParam)){
            // execute command
            mLoginTask.execute(loginRequestParam);
        }
    }

    public LiveData<UserInfo> onLoginSuccessObs() {
        return mLoginTask.onDataChanged();
    }

    public LiveData<Throwable> onLoginErrorObs() {
        return mLoginTask.onError();
    }

    public LiveData<Boolean> onLoadingObs() {
        return mLoginTask.onLoading();
    }

    public boolean isDataValid(LoginUseCase.LoginRequestParam param) {
        return param != null
                && mTextValidator.isUserNameValid(param.mUserName)
                && mTextValidator.isPwdValid(param.mPwd);
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
