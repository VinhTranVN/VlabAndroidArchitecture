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

    @Inject
    public UserRepositoryViewModel(UserRepositoryUseCase useCase){
        mUserRepositoryUC = useCase;
    }

    public void loadUserRepositories(UserRepositoryUseCase.RepositoryRequest repositoryRequest){
        mUserRepositoryUC.loadRepositories(repositoryRequest);
    }

    public LiveData<List<UserRepositoryModel>> onLoadUserReposSuccessObs(){
        return Transformations.map(
                mUserRepositoryUC.onLoadUserReposSuccessObs(),
                repoListResponse -> MapperUtil.mapList(repoListResponse, UserRepositoryModel::new));
    }

    public LiveData<Throwable> onLoadUserReposErrorObs(){
        return mUserRepositoryUC.onLoadUserReposErrorObs();
    }
}
