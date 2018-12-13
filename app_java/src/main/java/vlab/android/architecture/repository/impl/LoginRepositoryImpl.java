package vlab.android.architecture.repository.impl;

import android.util.Base64;

import javax.inject.Inject;

import io.reactivex.Observable;
import vlab.android.architecture.feature.user.UserModel;
import vlab.android.architecture.repository.LoginRepository;
import vlab.android.architecture.repository.SessionRepository;
import vlab.android.architecture.repository.source.remote.GitHubApi;
import vlab.android.architecture.repository.source.remote.response.UserResponse;

/**
 * Created by Vinh Tran on 2/14/18.
 */

public class LoginRepositoryImpl implements LoginRepository {

    private GitHubApi mApi;
    private SessionRepository mSessionRepository;

    @Inject
    public LoginRepositoryImpl(GitHubApi api, SessionRepository sessionRepository){
        mApi = api;
        mSessionRepository = sessionRepository;
    }

    @Override
    public Observable<UserResponse> login(String userName, String pwd) {
        String auth = userName + ":" + pwd;
        String authorization = "Basic " + Base64.encodeToString(auth.getBytes(), Base64.NO_WRAP);

        return mApi.login(authorization)
                .doOnNext(userResponse -> {
                    UserModel userModel = new UserModel(userResponse);
                    userModel.setAuth(authorization);
                    mSessionRepository.setUserSession(userModel);
                });
    }
}
