package vlab.android.architecture.feature.validator;

import android.text.TextUtils;

/**
 * Created by Vinh Tran on 10/24/18.
 * just demo
 */
public class TextValidator {
    public boolean isUserNameValid(String userName){
        if(!TextUtils.isEmpty(userName)){
            return true;
        }
        return false;
    }

    public boolean isPwdValid(String pwd) {
        if(!TextUtils.isEmpty(pwd)){
            return true;
        }
        return false;
    }
}
