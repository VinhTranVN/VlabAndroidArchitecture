package vlab.android.architecture.util

/**
 * Created by Vinh Tran on 10/24/18.
 * just demo
 */
object TextValidator {
    fun isUserNameValid(userName: String?): Boolean {
        return userName != null && "" != userName
    }

    fun isPwdValid(pwd: String?): Boolean {
        return pwd != null && "" != pwd
    }
}
