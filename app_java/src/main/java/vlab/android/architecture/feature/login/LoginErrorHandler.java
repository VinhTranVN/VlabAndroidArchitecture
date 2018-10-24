package vlab.android.architecture.feature.login;

import vlab.android.architecture.R;
import vlab.android.architecture.base.BaseErrorHandler;

/**
 * Created by Vinh Tran on 3/3/18.
 */

public class LoginErrorHandler extends BaseErrorHandler {

    @Override
    public String parseError(Throwable throwable){
        if(throwable == null){
            return mContext.getString(R.string.login_failed);
        }
        return mContext.getString(R.string.login_failed) + " : " + throwable.getMessage();
    }
}
