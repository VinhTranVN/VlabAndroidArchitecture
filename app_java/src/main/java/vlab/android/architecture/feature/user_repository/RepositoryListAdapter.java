package vlab.android.architecture.feature.user_repository;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vlab.android.architecture.R;
import vlab.android.architecture.base.BaseViewHolder;

/**
 * Created by Vinh Tran on 10/31/18.
 */
public class RepositoryListAdapter<T extends UserRepositoryModel> extends RecyclerView.Adapter {

    private List<T> mItemList = new ArrayList<>();
    private OnHolderItemActionListener mOnHolderItemActionListener;

    public RepositoryListAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ItemViewHolder(parent, mOnHolderItemActionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof RepositoryListAdapter.ItemViewHolder) {
            ((ItemViewHolder) viewHolder).bindData(mItemList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void setItemList(List<T> itemList) {
        if (itemList == null || itemList.size() == 0) {
            return;
        }
        mItemList.clear();
        mItemList.addAll(itemList);
        notifyDataSetChanged();
    }

    public void addItemList(List<T> itemList) {
        if (itemList == null || itemList.size() == 0) {
            return;
        }

        int startIndex = getItemCount();
        mItemList.addAll(itemList);
        notifyItemRangeInserted(startIndex, itemList.size());
    }

    public void clearList() {
        mItemList.clear();
        notifyDataSetChanged();
    }

    public void setOnHolderItemActionListener(OnHolderItemActionListener<T> listener) {
        mOnHolderItemActionListener = listener;
    }

    static class ItemViewHolder<ItemModel extends UserRepositoryModel> extends BaseViewHolder<ItemModel> {

        @BindView(R.id.tv_name) TextView mRepoName;
        @BindView(R.id.tv_description) TextView mRepoDescription;

        public ItemViewHolder(ViewGroup parent, OnHolderItemActionListener listener) {
            super(getView(parent));

            itemView.setOnClickListener(v -> {
                if(listener != null){
                    ItemModel userModel = (ItemModel) itemView.getTag();
                    listener.onHolderItemClicked(userModel);
                }
            });
        }

        private static View getView(ViewGroup parent) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
        }

        public void bindData(ItemModel item) {
            if (item == null) return;

            mRepoName.setText(item.getRepoName());
            mRepoDescription.setText(item.getDescription());

            itemView.setTag(item);
        }
    }

    public interface OnHolderItemActionListener<ItemModel> {
        void onHolderItemClicked(ItemModel itemModel);
    }
}
