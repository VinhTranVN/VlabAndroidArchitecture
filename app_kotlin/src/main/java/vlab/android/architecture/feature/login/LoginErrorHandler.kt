package vlab.android.architecture.feature.login

import vlab.android.architecture.R
import vlab.android.architecture.base.BaseErrorHandler

/**
 * Created by Vinh Tran on 3/3/18.
 */

class LoginErrorHandler : BaseErrorHandler() {

    override fun parseError(throwable: Throwable): String {
        return if (throwable == null) {
            mContext.getString(R.string.login_failed)
        } else {
            mContext.getString(R.string.login_failed) + " : " + throwable.message
        }
    }
}
