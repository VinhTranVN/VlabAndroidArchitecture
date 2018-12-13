package vlab.android.architecture.repository;

import vlab.android.architecture.feature.user.UserModel;

/**
* Created by Vinh Tran on 2/7/18.
*/

public interface SessionRepository {
    void setUserSession(UserModel userModel);
    UserModel getUserSession();
    boolean isLoggedIn();
}
