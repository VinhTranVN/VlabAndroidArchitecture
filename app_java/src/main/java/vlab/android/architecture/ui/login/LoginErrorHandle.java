package vlab.android.architecture.ui.login;

import android.content.Context;

import vlab.android.architecture.R;

/**
 * Created by Vinh Tran on 3/3/18.
 */

public class LoginErrorHandle {

    private Context mContext;

    public LoginErrorHandle(Context context) {
        mContext = context;
    }

    public String parseError(Throwable throwable){
        return mContext.getString(R.string.login_failed) + " : " + throwable.getMessage();
    }
}
