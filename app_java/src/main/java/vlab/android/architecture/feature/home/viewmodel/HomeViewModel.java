package vlab.android.architecture.feature.home.viewmodel;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseViewModel;

/**
 * Created by Vinh.Tran on 11/14/18.
 **/
public class HomeViewModel extends BaseViewModel {

    private String mUserName;
    private boolean mIsUserAuthenticated;

    @Inject
    public HomeViewModel(){

    }

    public void setUserName(String userName) {
        mUserName = userName;
        mIsUserAuthenticated = (mUserName != null && !mUserName.isEmpty());
    }

    public boolean isUserAuthenticated() {
        return mIsUserAuthenticated;
    }

    public String getUserName() {
        return mIsUserAuthenticated ? mUserName : "Welcome Guest";
    }
}
