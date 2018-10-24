package vlab.android.architecture.feature.validator;

/**
 * Created by Vinh Tran on 10/24/18.
 * just demo
 */
public class TextValidator {
    public boolean isUserNameValid(String userName){
        if(userName != null && !"".equals(userName)){
            return true;
        }
        return false;
    }

    public boolean isPwdValid(String pwd) {
        if(pwd != null && !"".equals(pwd)){
            return true;
        }
        return false;
    }
}
