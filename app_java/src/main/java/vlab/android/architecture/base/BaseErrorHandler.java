package vlab.android.architecture.base;

import android.content.Context;

import vlab.android.architecture.MyApplication;
import vlab.android.architecture.R;

/**
 * Created by Vinh Tran on 10/24/18.
 */
public class BaseErrorHandler {

    protected Context mContext;

    public BaseErrorHandler() {
        mContext = MyApplication.getInstance().getApplicationContext();
    }

    public String parseError(Throwable throwable) {
        return mContext.getString(R.string.error_common);
    }

}
