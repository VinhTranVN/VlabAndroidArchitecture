package vlab.android.architecture.feature.user_repository;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import vlab.android.architecture.R;
import vlab.android.architecture.base.BaseFragment;
import vlab.android.common.util.InfiniteScrollListener;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class RepositoryFragment extends BaseFragment {

    @BindView(R.id.swipe_to_refresh) SwipeRefreshLayout mSwipeToRefreshView;
    @BindView(R.id.recycleView) RecyclerView mRecyclerView;
    @BindView(R.id.progressBar) ProgressBar mLoadingView;

    private RepositoryVM mViewModel;

    private RepositoryListAdapter<RepositoryModel> mAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_repository;
    }

    @Override
    protected void initViewModel() {
        mViewModel = provideViewModel(RepositoryVM.class);
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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mAdapter = new RepositoryListAdapter<>();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnHolderItemActionListener(repositoryModel -> {
            // TODO
        });

        mRecyclerView.addOnScrollListener(new InfiniteScrollListener(mRecyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore() {
                mViewModel.loadMoreRepos();
            }
        });

        mSwipeToRefreshView.setOnRefreshListener(() -> {
            startLoadRepos();
        });

        startLoadRepos();
    }

    private void startLoadRepos() {
        mViewModel.startLoadRepository();
    }
}
