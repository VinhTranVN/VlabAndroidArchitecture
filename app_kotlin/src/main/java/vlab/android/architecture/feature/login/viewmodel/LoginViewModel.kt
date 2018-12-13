package vlab.android.architecture.feature.login.viewmodel

import android.arch.lifecycle.LiveData
import vlab.android.architecture.base.BaseViewModel
import vlab.android.architecture.feature.login.use_case.LoginUseCase
import vlab.android.architecture.feature.user.UserInfo
import javax.inject.Inject

/**
 * Created by Vinh Tran on 2/15/18.
 */

class LoginViewModel @Inject
constructor(private val mLoginUseCase: LoginUseCase) : BaseViewModel() {

    override fun onCleared() {
        mLoginUseCase.onCleared()
        super.onCleared()
    }

    fun login(userName: String, pwd: String) {
        // map request data
        val requestData = LoginUseCase.LoginRequestParam(userName, pwd)

        mLoginUseCase.login(requestData)
    }

    fun cancelLogin() {
        mLoginUseCase.cancelLoginRequest()
    }

    fun onLoginSuccessObs(): LiveData<UserInfo> {
        return mLoginUseCase.onLoginSuccessObs()
    }

    fun onLoginErrorObs(): LiveData<Throwable> {
        return mLoginUseCase.onLoginErrorObs()
    }

    fun onLoadingObs(): LiveData<Boolean> {
        return mLoginUseCase.onLoadingObs()
    }
}


