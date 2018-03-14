package vlab.android.architecture.ui.login;

import android.arch.lifecycle.LiveData;

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

    private RxCommand<LoginRequestParam, UserInfo> mLoginCommand;

    // request param
    private LoginRequestParam mLoginRequestParam = new LoginRequestParam();

    @Inject
    public LoginViewModel(LoginRepository repository){
        LogUtils.d(getClass().getSimpleName(), ">>> public LoginViewModel: ");

        mLoginCommand = new RxCommand<>(mLoginRequestParam, requestParam ->
                repository.login(requestParam.userName, requestParam.pwd)
        );

        addSubscriptions(
                mLoginCommand.subscribe()
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
        return mLoginCommand.onDataChanged();
    }

    public LiveData<Throwable> onLoginFailedObs() {
        return mLoginCommand.onError();
    }

    public LiveData<Boolean> onLoadingObs() {
        return mLoginCommand.onLoading();
    }

//    static class LoginViewModelFactory implements ViewModelProvider.Factory {
//
//        private LoginRepository mLoginRepository;
//
//        @Inject
//        public LoginViewModelFactory(LoginRepository loginRepository) {
//            mLoginRepository = loginRepository;
//        }
//
//        @NonNull
//        @Override
//        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//            return (T) new LoginViewModel(mLoginRepository);
//        }
//    }

    // request params
    static class LoginRequestParam {
        String userName;
        String pwd;
    }
}


