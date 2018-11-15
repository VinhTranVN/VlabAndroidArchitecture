package vlab.android.architecture.base;

import vlab.android.common.util.LogUtils;

/**
 * Created by Vinh.Tran on 10/25/18.
 **/
public class BaseUseCase {
    /**
     * clear resource hear
     */
    protected void onCleared(){
        LogUtils.d(getClass().getSimpleName(), ">>> onCleared ");
    }
}
