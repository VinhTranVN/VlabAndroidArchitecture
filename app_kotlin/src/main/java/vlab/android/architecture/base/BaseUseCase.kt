package vlab.android.architecture.base

import vlab.android.common.util.LogUtils

/**
 * Created by Vinh.Tran on 10/25/18.
 */
open class BaseUseCase {
    /**
     * clear resource hear
     */
    protected open fun onCleared() {
        LogUtils.d(javaClass.simpleName, ">>> onCleared ")
    }
}
