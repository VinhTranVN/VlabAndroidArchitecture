package vlab.android.architecture.util;

/**
 * Created by Vinh Tran on 10/24/18.
 * just demo
 */
public class TextValidator {
    public static boolean isUserNameValid(String userName){
        return userName != null && !"".equals(userName);
    }

    public static boolean isPwdValid(String pwd) {
        return pwd != null && !"".equals(pwd);
    }
}
