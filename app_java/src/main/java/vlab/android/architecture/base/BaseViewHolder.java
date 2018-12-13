package vlab.android.architecture.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Vinh Tran on 2/12/18.
 */
public abstract class BaseViewHolder<ItemModel> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    abstract protected void bindData(ItemModel item);
}