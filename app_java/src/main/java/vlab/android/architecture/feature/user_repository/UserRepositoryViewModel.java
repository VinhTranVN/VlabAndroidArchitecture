package vlab.android.architecture.feature.user_repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import java.util.List;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseViewModel;
import vlab.android.architecture.util.MapperUtil;

/**
 * Created by Vinh.Tran on 11/14/18.
 **/
public class UserRepositoryViewModel extends BaseViewModel {

    private UserRepositoryUseCase mUserRepositoryUC;
    private boolean mIsFirstLoad = true;

    @Inject
    public UserRepositoryViewModel(UserRepositoryUseCase useCase){
        mUserRepositoryUC = useCase;
    }

    public void loadUserRepositories(UserRepositoryUseCase.RepositoryRequest repositoryRequest) {
        mIsFirstLoad = true;
        mUserRepositoryUC.loadRepositories(repositoryRequest);
    }

    public LiveData<List<UserRepositoryModel>> onLoadUserReposSuccessObs(){
        return Transformations.map(
                mUserRepositoryUC.onFirstLoadRepoListSuccessObs(),
                repoListResponse -> MapperUtil.mapList(repoListResponse, UserRepositoryModel::new));
    }

    public LiveData<Throwable> onLoadUserReposErrorObs(){
        return mUserRepositoryUC.onLoadUserReposErrorObs();
    }

    public LiveData<List<UserRepositoryModel>> onFirstLoadRepoListSuccessObs() {
        return Transformations.map(
                mUserRepositoryUC.onFirstLoadRepoListSuccessObs(),
                dataResponse -> {
                    mIsFirstLoad = false;
                    return MapperUtil.mapList(dataResponse, UserRepositoryModel::new);
                }
        );
    }

    public LiveData<List<UserRepositoryModel>> onLoadMoreRepoListSuccessObs() {
        return Transformations.map(
                mUserRepositoryUC.onFirstLoadRepoListSuccessObs(),
                dataResponse -> MapperUtil.mapList(dataResponse, UserRepositoryModel::new)
        );
    }

    public LiveData<Boolean> onLoadingRepoListObs() {
        return mUserRepositoryUC.onLoadingRepoListObs();
    }

    public LiveData<Throwable> onLoadRepoListFailedObs() {
        return mUserRepositoryUC.onLoadRepoListFailedObs();
    }
}
