package vlab.android.architecture.feature.home.viewmodel;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseViewModel;

/**
 * Created by Vinh.Tran on 11/14/18.
 **/
public class HomeViewModel extends BaseViewModel {

    private String mUserName;

    @Inject
    public HomeViewModel(){

    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserName() {
        return mUserName == null ? "Welcome Guest" : mUserName;
    }
}
