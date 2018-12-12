package vlab.android.common.util;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Vinh Tran on 10/31/18.
 */
public abstract class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    int mFirstVisibleItem, mVisibleItemCount, mTotalItemCount;

    private RecyclerView.LayoutManager mLayoutManager;

    public InfiniteScrollListener(RecyclerView.LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy < 0) return;

        mVisibleItemCount = recyclerView.getChildCount();
        mTotalItemCount = mLayoutManager.getItemCount();
        if (mLayoutManager instanceof LinearLayoutManager) {
            mFirstVisibleItem = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        } else if (mLayoutManager instanceof GridLayoutManager) {
            mFirstVisibleItem = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        }

        if (mTotalItemCount - (mFirstVisibleItem + mVisibleItemCount) <= getLastInvisibleItemCount()) {
            // End has been reached
            onLoadMore();
        }
    }

    /**
     * The minimum amount of last items to have below your current scroll position before mLoading more.
     *
     * @return Default when remain 1 items will trigger load more
     */
    public int getLastInvisibleItemCount() {
        return 1;
    }

    public abstract void onLoadMore();
}
