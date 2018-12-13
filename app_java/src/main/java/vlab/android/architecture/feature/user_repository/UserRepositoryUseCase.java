package vlab.android.architecture.feature.user_repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseUseCase;
import vlab.android.architecture.feature.user.UserModel;
import vlab.android.architecture.repository.GithubRepository;
import vlab.android.architecture.repository.SessionRepository;
import vlab.android.architecture.repository.source.remote.response.RepositoryResponse;
import vlab.android.common.util.RxTask;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class UserRepositoryUseCase extends BaseUseCase {

    private static final String ITEM_PER_PAGE = "5";
    private static final String ITEM_SORT_FILTER = "updated";

    private RxTask<RepositoryRequest, List<RepositoryResponse>> mLoadReposTask;

    @Inject
    public UserRepositoryUseCase(GithubRepository repository, SessionRepository sessionRepository){

        UserModel userSession = sessionRepository.getUserSession();

        mLoadReposTask = new RxTask<>(dataRequest ->
                repository.getUserRepositories(
                        userSession.getAuth(),
                        dataRequest.sort,
                        dataRequest.page,
                        dataRequest.perPage
        ));
    }

    public void loadRepositories(RepositoryRequest repositoryRequest){
        mLoadReposTask.execute(repositoryRequest);
    }

    public LiveData<List<RepositoryResponse>> onFirstLoadRepoListSuccessObs(){
        return mLoadReposTask.onSingleLiveDataChanged();
    }

    public LiveData<List<RepositoryResponse>> onLoadMoreRepoListSuccessObs(){
        // TODO
        return mLoadReposTask.onSingleLiveDataChanged();
    }

    public LiveData<Throwable> onLoadUserReposErrorObs(){
        return mLoadReposTask.onError();
    }

    public LiveData<Boolean> onLoadingRepoListObs() {
        return mLoadReposTask.onLoading();
    }

    public LiveData<Throwable> onLoadRepoListFailedObs() {
        return mLoadReposTask.onError();
    }

    public static class RepositoryRequest {
        String sort = ITEM_SORT_FILTER;
        String perPage = ITEM_PER_PAGE;
        String page;

        public RepositoryRequest(String page) {
            this.page = page;
        }

        public RepositoryRequest(String sort, String perPage, String page) {
            this.sort = sort;
            this.perPage = perPage;
            this.page = page;
        }
    }
}
