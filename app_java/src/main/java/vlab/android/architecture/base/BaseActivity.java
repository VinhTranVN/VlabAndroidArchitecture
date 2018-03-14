package vlab.android.architecture.base;

import android.os.Bundle;

import vlab.android.common.ui.CommonActivity;
import vlab.android.common.util.LogUtils;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public class BaseActivity extends CommonActivity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(getClass().getSimpleName(), ">>> onCreate");
    }
}
