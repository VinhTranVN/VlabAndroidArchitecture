package vlab.android.architecture.repository.impl;

import java.util.List;

import io.reactivex.Observable;
import vlab.android.architecture.repository.GithubRepository;
import vlab.android.architecture.repository.source.remote.response.RepositoryResponse;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class GithubRepositoryImpl implements GithubRepository {
    @Override
    public Observable<List<RepositoryResponse>> getUserRepositories(String authorization, String sort, String pageIndex, String perPage) {
        return null;
    }
}
