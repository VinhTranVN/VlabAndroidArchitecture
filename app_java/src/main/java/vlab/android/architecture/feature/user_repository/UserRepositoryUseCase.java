package vlab.android.architecture.feature.user_repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseUseCase;
import vlab.android.architecture.repository.GithubRepository;
import vlab.android.architecture.repository.source.remote.response.RepositoryResponse;
import vlab.android.common.util.RxTask;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class UserRepositoryUseCase extends BaseUseCase {

    private RxTask<RepositoryRequest, List<RepositoryResponse>> mLoadReposTask;

    @Inject
    public UserRepositoryUseCase(GithubRepository repository){
        mLoadReposTask = new RxTask<>(dataRequest ->
                repository.getUserRepositories(dataRequest.auth, dataRequest.sort, dataRequest.page, dataRequest.perPage
        ));
    }

    public void loadRepositories(RepositoryRequest repositoryRequest){
        mLoadReposTask.execute(repositoryRequest);
    }

    public LiveData<List<RepositoryResponse>> onLoadUserReposSuccessObs(){
        return mLoadReposTask.onSingleLiveDataChanged();
    }

    public LiveData<Throwable> onLoadUserReposErrorObs(){
        return mLoadReposTask.onError();
    }

    class RepositoryRequest {
        String auth;
        String sort;
        String page;
        String perPage;
    }
}
