package vlab.android.architecture.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Vinh Tran on 2/12/18.
 */
public abstract class BaseViewHolder<ItemModel> extends RecyclerView.ViewHolder {
    BaseViewHolder(View itemView) {
        super(itemView);
    }
    abstract void bindData(ItemModel item);
}