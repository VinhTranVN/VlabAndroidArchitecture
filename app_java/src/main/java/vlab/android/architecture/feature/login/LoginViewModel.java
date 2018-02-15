package vlab.android.architecture.feature.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseViewModel;
import vlab.android.architecture.data.repository.LoginRepository;
import vlab.android.architecture.model.UserInfo;
import vlab.android.architecture.util.LogUtils;
import vlab.android.common.util.RxCommand;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public class LoginViewModel extends BaseViewModel {

    private LoginRepository mRepository;

    private RxCommand<UserInfo> mLoginCommand;
    private MutableLiveData<UserInfo> mOnLoginObs = new MutableLiveData<>();
    private String mUserName;
    private String mPwd;

    public LoginViewModel(LoginRepository repository){
        LogUtils.d(getClass().getSimpleName(), ">>> public LoginViewModel: ");
        mRepository = repository;

        mLoginCommand = new RxCommand<>(() ->
                mRepository.login(mUserName, mPwd)
                    .doOnNext(userInfo -> {
                        LogUtils.d(getClass().getSimpleName(), ">>> doOnNext: " + userInfo.toString());
                        mOnLoginObs.postValue(userInfo);
                    }));
        mLoginCommand.onExecuted().subscribe();
    }

    public void login(String userName, String pwd){
        mUserName = userName;
        mPwd = pwd;
        mLoginCommand.execute();
    }

    public MutableLiveData<UserInfo> onLoginSuccessObs() {
        return mOnLoginObs;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

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
}
