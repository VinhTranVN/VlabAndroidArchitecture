package vlab.android.architecture.base

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Vinh Tran on 2/12/18.
 */
abstract class BaseViewHolder<ItemModel> : RecyclerView.ViewHolder {
    fun getLayoutRes() : Int {
        return 0;
    }
    constructor(itemView: View?) : super(itemView)
    abstract fun bindData(item: ItemModel);
}