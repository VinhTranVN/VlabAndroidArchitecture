package vlab.android.architecture.model;

import vlab.android.architecture.repository.source.remote.response.UserResponse;

/**
 * Created by Vinh Tran on 2/15/18.
 */

public class UserInfo {

    String userName;

    public UserInfo() {
    }

    public UserInfo(UserResponse userResponse) {
        this.userName = userResponse.getName();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
