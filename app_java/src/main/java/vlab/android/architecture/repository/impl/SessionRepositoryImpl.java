package vlab.android.architecture.repository.impl;

import javax.inject.Inject;

import vlab.android.architecture.feature.login.model.UserModel;
import vlab.android.architecture.repository.SessionRepository;

/**
 * Created by Vinh.Tran on 12/12/18.
 **/
public class SessionRepositoryImpl implements SessionRepository {

    private UserModel mUserModel;

    @Inject
    public SessionRepositoryImpl() {

    }

    @Override
    public void setUserSession(UserModel userModel) {
        mUserModel = userModel;
    }

    @Override
    public UserModel getUserSession() {
        return mUserModel;
    }

    @Override
    public boolean isLoggedIn() {
        return mUserModel != null;
    }
}
