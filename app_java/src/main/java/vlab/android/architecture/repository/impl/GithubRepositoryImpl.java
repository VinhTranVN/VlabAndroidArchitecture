package vlab.android.architecture.repository.impl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import vlab.android.architecture.repository.GithubRepository;
import vlab.android.architecture.repository.source.remote.GitHubApi;
import vlab.android.architecture.repository.source.remote.response.RepositoryResponse;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class GithubRepositoryImpl implements GithubRepository {

    private GitHubApi mApi;

    @Inject
    public GithubRepositoryImpl(GitHubApi api) {
        mApi = api;
    }

    @Override
    public Observable<List<RepositoryResponse>> getUserRepositories(String authorization, String sort, String pageIndex, String perPage) {
        return mApi.getRepositories(authorization,sort, pageIndex, perPage);
    }
}
