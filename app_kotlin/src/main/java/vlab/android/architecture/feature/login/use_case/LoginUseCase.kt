package vlab.android.architecture.feature.login.use_case

import android.arch.lifecycle.LiveData
import vlab.android.architecture.base.BaseUseCase
import vlab.android.architecture.feature.user.UserInfo
import vlab.android.architecture.repository.LoginRepository
import vlab.android.architecture.util.TextValidator
import vlab.android.common.util.RxTask
import javax.inject.Inject

/**
 * Created by Vinh.Tran on 10/25/18.
 */
class LoginUseCase @Inject
constructor(repository: LoginRepository) : BaseUseCase() {

    private val mLoginTask: RxTask<LoginRequestParam, UserInfo>

    init {

        mLoginTask = RxTask { requestData -> repository.login(requestData.mUserName, requestData.mPwd) }
    }

    public override fun onCleared() {
        mLoginTask.destroy()
        super.onCleared()
    }

    fun login(loginRequestParam: LoginUseCase.LoginRequestParam) {
        // update params
        if (isDataValid(loginRequestParam)) {
            // execute command
            mLoginTask.execute(loginRequestParam)
        }
    }

    fun cancelLoginRequest() {
        mLoginTask.cancel()
    }

    fun onLoginSuccessObs(): LiveData<UserInfo> {
        return mLoginTask.onSingleLiveDataChanged()
    }

    fun onLoginErrorObs(): LiveData<Throwable> {
        return mLoginTask.onError()
    }

    fun onLoadingObs(): LiveData<Boolean> {
        return mLoginTask.onLoading()
    }

    fun isDataValid(param: LoginUseCase.LoginRequestParam?): Boolean {
        return (param != null
                && TextValidator.isUserNameValid(param.mUserName)
                && TextValidator.isPwdValid(param.mPwd))
    }

    // request params
    class LoginRequestParam {
        internal lateinit var mUserName: String
        internal lateinit var mPwd: String

        constructor() {}

        constructor(userName: String, pwd: String) {
            this.mUserName = userName
            this.mPwd = pwd
        }
    }
}
