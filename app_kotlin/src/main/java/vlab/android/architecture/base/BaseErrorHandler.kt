package vlab.android.architecture.base

import android.content.Context

import vlab.android.architecture.MyApplication
import vlab.android.architecture.R

/**
 * Created by Vinh Tran on 10/24/18.
 */
open class BaseErrorHandler {

    var mContext: Context

    init {
        mContext = MyApplication.getInstance().applicationContext
    }

    open fun parseError(throwable: Throwable): String {
        return mContext.getString(R.string.error_common)
    }

}
