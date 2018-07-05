package vlab.android.architecture.ui.login;

import android.content.Context;

import vlab.android.architecture.MyApplication;
import vlab.android.architecture.R;

/**
 * Created by Vinh Tran on 3/3/18.
 */

public class LoginErrorHandler {

    private Context mContext;

    public LoginErrorHandler() {
        mContext = MyApplication.getInstance().getApplicationContext();
    }

    public String parseError(Throwable throwable){
        if(throwable == null){
            return mContext.getString(R.string.login_failed);
        }
        return mContext.getString(R.string.login_failed) + " : " + throwable.getMessage();
    }
}
