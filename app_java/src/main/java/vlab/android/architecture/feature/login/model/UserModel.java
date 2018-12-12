package vlab.android.architecture.feature.login.model;

import vlab.android.architecture.repository.source.remote.response.UserResponse;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public class UserModel {

    String userName;
    String auth;

    public UserModel() {
    }

    public UserModel(UserResponse userResponse) {
        this.userName = userResponse.getName();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }
}
