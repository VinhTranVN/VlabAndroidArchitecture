package vlab.android.architecture.ui.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseViewModel;
import vlab.android.architecture.model.UserInfo;
import vlab.android.architecture.repository.LoginRepository;
import vlab.android.common.util.RxCommand;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public class LoginViewModel extends BaseViewModel {

    private RxCommand<LoginRequestParam, UserInfo> mLoginCommand;
    MutableLiveData<UserInfo> mOnLoginSuccessObs = new MutableLiveData<>();
    MutableLiveData<Throwable> mOnLoginErrorObs = new MutableLiveData<>();

    // request param
    private LoginRequestParam mLoginRequestParam = new LoginRequestParam();

    @Inject
    public LoginViewModel(LoginRepository repository){
        mLoginCommand = new RxCommand<>(mLoginRequestParam, requestParam ->
                repository.login(requestParam.userName, requestParam.pwd)
        );

        // add subscriptions
        addSubscriptions(
                mLoginCommand.subscribe()
        );
    }

    public void startObservers(){
        if (!mLoginCommand.onDataChanged().hasObservers()) {
            mLoginCommand.onDataChanged().observe(mLifeCycleOwner, userInfoResponse -> {
                if(userInfoResponse.getData() != null){
                    mOnLoginSuccessObs.postValue(userInfoResponse.getData());
                } else {
                    mOnLoginErrorObs.postValue(userInfoResponse.getError());
                }
            });
        }
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
        return mOnLoginSuccessObs;
    }

    public LiveData<Throwable> onLoginErrorObs() {
        return mOnLoginErrorObs;
    }

    public LiveData<Boolean> onLoadingObs() {
        return mLoginCommand.onLoading();
    }

    public boolean isDataValid(LoginRequestParam param) {

        if(param == null || param.userName == null || param.userName.isEmpty()
                || param.pwd == null || param.pwd.isEmpty()){
            return false;
        }
        return true;
    }

    // request params
    static class LoginRequestParam {
        String userName;
        String pwd;

        public LoginRequestParam() {}

        public LoginRequestParam(String userName, String pwd) {
            this.userName = userName;
            this.pwd = pwd;
        }
    }
}


