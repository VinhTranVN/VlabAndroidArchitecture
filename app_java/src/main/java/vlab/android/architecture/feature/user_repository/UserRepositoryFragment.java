package vlab.android.architecture.feature.user_repository;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import vlab.android.architecture.R;
import vlab.android.architecture.base.BaseFragment;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class UserRepositoryFragment extends BaseFragment {

    @BindView(R.id.swipe_to_refresh) SwipeRefreshLayout mSwipeToRefreshView;
    @BindView(R.id.recycleView) RecyclerView mRecyclerView;
    @BindView(R.id.progressBar) ProgressBar mLoadingView;

    private UserRepositoryViewModel mViewModel;

    private RepositoryListAdapter<UserRepositoryModel> mAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_repository;
    }

    @Override
    protected void initViewModel() {
        mViewModel = provideViewModel(UserRepositoryViewModel.class);
    }

    @Override
    protected void bindViewModel() {
        mViewModel.onFirstLoadRepoListSuccessObs()
                .observe(this, items -> {
                    mSwipeToRefreshView.setRefreshing(false);
                    mAdapter.setItemList(items);
                });

        mViewModel.onLoadMoreRepoListSuccessObs()
                .observe(this, items -> mAdapter.addItemList(items));

        // handle loading api
        mViewModel.onLoadingRepoListObs()
                .observe(this, isLoading -> {
                    mLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                    if(!isLoading){
                        mSwipeToRefreshView.setRefreshing(false);
                    }
                });

        // handle error
        mViewModel.onLoadRepoListFailedObs().observe(this, throwable -> showErrorMsg(throwable));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RepositoryListAdapter<>();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnHolderItemActionListener(repositoryModel -> {
            // TODO
        });

        mViewModel.loadUserRepositories(new UserRepositoryUseCase.RepositoryRequest("0"));
    }
}
