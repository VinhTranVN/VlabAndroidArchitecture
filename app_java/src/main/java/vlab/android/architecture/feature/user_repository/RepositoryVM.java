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
public class RepositoryVM extends BaseViewModel {

    private RepositoryUseCase mUserRepositoryUC;

    @Inject
    public RepositoryVM(RepositoryUseCase useCase){
        mUserRepositoryUC = useCase;
    }

    public void startLoadRepository() {
        mUserRepositoryUC.startLoadRepos();
    }

    public void loadMoreRepos() {
        mUserRepositoryUC.loadMoreRepos();
    }

    public LiveData<Throwable> onLoadUserReposErrorObs(){
        return mUserRepositoryUC.onLoadUserReposErrorObs();
    }

    public LiveData<List<RepositoryModel>> onFirstLoadRepoListSuccessObs() {
        return Transformations.map(
                mUserRepositoryUC.onFirstLoadRepoListSuccessObs(),
                items -> MapperUtil.mapList(items, RepositoryModel::new)
        );
    }

    public LiveData<List<RepositoryModel>> onLoadMoreRepoListSuccessObs() {
        return Transformations.map(
                mUserRepositoryUC.onLoadMoreRepoListSuccessObs(),
                items -> MapperUtil.mapList(items, RepositoryModel::new)
        );
    }

    public LiveData<Boolean> onLoadingRepoListObs() {
        return mUserRepositoryUC.onLoadingRepoListObs();
    }

    public LiveData<Throwable> onLoadRepoListFailedObs() {
        return mUserRepositoryUC.onLoadRepoListFailedObs();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mUserRepositoryUC.onCleared();
    }
}
