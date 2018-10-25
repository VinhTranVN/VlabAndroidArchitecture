package vlab.android.architecture.util;

/**
 * Created by Vinh Tran on 10/24/18.
 * just demo
 */
public class TextValidator {
    public static boolean isUserNameValid(String userName){
        if(userName != null && !"".equals(userName)){
            return true;
        }
        return false;
    }

    public static boolean isPwdValid(String pwd) {
        if(pwd != null && !"".equals(pwd)){
            return true;
        }
        return false;
    }
}
