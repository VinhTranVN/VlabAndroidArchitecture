package vlab.android.architecture.feature.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseViewModel;
import vlab.android.architecture.data.repository.LoginRepository;
import vlab.android.architecture.model.UserInfo;
import vlab.android.common.util.LogUtils;
import vlab.android.common.util.RxCommand;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public class LoginViewModel extends BaseViewModel {

    private LoginRepository mRepository;

    private RxCommand<LoginRequestParam, UserInfo> mLoginCommand;
    private MutableLiveData<UserInfo> mOnLoginObs = new MutableLiveData<>();
    private MutableLiveData<Boolean> mOnLoadingObs = new MutableLiveData<>();
    private MutableLiveData<Throwable> mOnErrorObs = new MutableLiveData<>();

    // request param
    private LoginRequestParam mLoginRequestParam = new LoginRequestParam();

    public LoginViewModel(LoginRepository repository){
        LogUtils.d(getClass().getSimpleName(), ">>> public LoginViewModel: ");
        mRepository = repository;

        mLoginCommand = new RxCommand<>(mLoginRequestParam, requestParam ->
                mRepository.login(requestParam.userName, requestParam.pwd)
                        .doOnNext(userInfo -> {
                            LogUtils.d(LoginViewModel.this.getClass().getSimpleName(), ">>> doOnNext: " + userInfo.toString());
                            mOnLoginObs.postValue(userInfo);
                        })
        );

        addSubscriptions(
                mLoginCommand.onExecuted().subscribe(),

                mLoginCommand.onExecuting()
                        .doOnNext(isLoading -> mOnLoadingObs.postValue(isLoading))
                        .subscribe(),

                mLoginCommand.onError()
                        .doOnNext(throwable -> mOnErrorObs.postValue(throwable))
                        .subscribe()
        );

    }

    public void login(String userName, String pwd){

        // update params
        mLoginRequestParam.userName = userName;
        mLoginRequestParam.pwd = pwd;

        // execute command
        mLoginCommand.execute();
    }

    public LiveData<UserInfo> onLoginSuccessObs() {
        return mOnLoginObs;
    }

    public LiveData<Boolean> onLoadingObs() {
        return mOnLoadingObs;
    }

    public LiveData<Throwable> onErrorObs() {
        return mOnErrorObs;
    }

    static class LoginViewModelFactory implements ViewModelProvider.Factory {

        private LoginRepository mLoginRepository;

        @Inject
        public LoginViewModelFactory(LoginRepository loginRepository) {
            mLoginRepository = loginRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new LoginViewModel(mLoginRepository);
        }
    }

    // request params
    static class LoginRequestParam {
        String userName;
        String pwd;
    }
}


