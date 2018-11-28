package vlab.android.architecture.base

import android.os.Bundle

import vlab.android.common.ui.DaggerCommonActivity
import vlab.android.common.util.LogUtils

/**
 * Created by Vinh Tran on 2/15/18.
 */

open class BaseActivity : DaggerCommonActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(javaClass.simpleName, ">>> onCreate")
    }
}