package vlab.android.architecture.feature.user_repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import vlab.android.architecture.base.BaseUseCase;
import vlab.android.architecture.feature.user.UserModel;
import vlab.android.architecture.repository.GithubRepository;
import vlab.android.architecture.repository.SessionRepository;
import vlab.android.architecture.repository.source.remote.response.RepositoryResponse;
import vlab.android.common.util.LogUtils;
import vlab.android.common.util.RxTask;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class RepositoryUseCase extends BaseUseCase {

    private static final int ITEM_PER_PAGE = 10;
    private static final String ITEM_SORT_FILTER = "updated";

    private RxTask<RepositoryRequest, List<RepositoryResponse>> mLoadReposTask;

    private MutableLiveData<List<RepositoryResponse>> mLoadFirstUserList = new MutableLiveData<>();
    private MutableLiveData<List<RepositoryResponse>> mLoadMoreUserList = new MutableLiveData<>();
    private RepositoryRequest mDataRequest;
    private int mTotalItem = 0;
    private boolean mHasLoadMore = true;

    @Inject
    public RepositoryUseCase(GithubRepository repository, SessionRepository sessionRepository){
        mDataRequest = new RepositoryRequest(0);

        UserModel userSession = sessionRepository.getUserSession();

        mLoadReposTask = new RxTask<>(dataRequest ->
                repository.getUserRepositories(userSession.getAuth(), dataRequest.sort, String.valueOf(dataRequest.mPageIndex), String.valueOf(dataRequest.mPageSize))
                        .map(pagingDataResponse -> {
                            // update next since value
                            String nextPage = pagingDataResponse.getNextPage();
                            if (nextPage != null && !nextPage.isEmpty()) {
                                mDataRequest.mPageIndex = Integer.valueOf(nextPage);
                            }

                            List<RepositoryResponse> items = pagingDataResponse.getItems();

                            if (nextPage == null || items == null || items.size() < dataRequest.mPageSize) {
                                mHasLoadMore = false;
                                LogUtils.d(getClass().getSimpleName(), ">>> UserListUseCase: mHasLoadMore " + mHasLoadMore);
                            }

                            return items;
                        })
                        .doOnNext(items -> {

                            mTotalItem += items.size();

                            if(mTotalItem <= dataRequest.mPageSize){
                                // allow first page emit data
                                mLoadFirstUserList.postValue(items);
                            } else {
                                // otherwise load more
                                mLoadMoreUserList.postValue(items);
                            }
                        })
        );
    }

    public void startLoadRepos(){
        mDataRequest.mPageIndex = 0;
        mTotalItem = 0;
        mHasLoadMore = true;
        mLoadReposTask.execute(mDataRequest);
    }

    public void loadMoreRepos(){
        if (mHasLoadMore) {
            mLoadReposTask.execute(mDataRequest);
        }
    }

    public LiveData<List<RepositoryResponse>> onFirstLoadRepoListSuccessObs(){
        return mLoadFirstUserList;
    }

    public LiveData<List<RepositoryResponse>> onLoadMoreRepoListSuccessObs(){
        return mLoadMoreUserList;
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
        int mPageSize = ITEM_PER_PAGE;
        int mPageIndex;

        public RepositoryRequest(int pageIndex) {
            this.mPageIndex = pageIndex;
        }

        public RepositoryRequest(String sort, int pageSize, int pageIndex) {
            this.sort = sort;
            this.mPageSize = pageSize;
            this.mPageIndex = pageIndex;
        }
    }
}
