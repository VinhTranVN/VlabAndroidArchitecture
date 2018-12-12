package vlab.android.architecture.repository.impl;

import android.util.Base64;

import javax.inject.Inject;

import io.reactivex.Observable;
import vlab.android.architecture.repository.LoginRepository;
import vlab.android.architecture.repository.source.remote.GitHubApi;
import vlab.android.architecture.repository.source.remote.response.UserResponse;

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
    public Observable<UserResponse> login(String userName, String pwd) {
        String auth = userName + ":" + pwd;
        String authorization = "Basic " + Base64.encodeToString(auth.getBytes(), Base64.NO_WRAP);

        return mApi.login(authorization);
    }
}
