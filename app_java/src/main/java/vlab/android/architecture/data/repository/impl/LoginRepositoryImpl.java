package vlab.android.architecture.data.repository.impl;

import android.util.Base64;

import javax.inject.Inject;

import rx.Observable;
import vlab.android.architecture.data.repository.LoginRepository;
import vlab.android.architecture.data.source.remote.GitHubApi;
import vlab.android.architecture.model.UserInfo;

/**
 * Created by Vinh Tran on 2/14/18.
 */

public class LoginRepositoryImpl implements LoginRepository {

    private GitHubApi mApi;

    @Inject
    public LoginRepositoryImpl(GitHubApi api){
        mApi = api;
    }

    @Override
    public Observable<UserInfo> login(String userName, String pwd) {
        String auth = userName + ":" + pwd;
        return mApi.login("Basic " + Base64.encodeToString(auth.getBytes(), Base64.NO_WRAP))
                .map(userResponse -> new UserInfo(userResponse));
    }
}
