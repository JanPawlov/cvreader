package com.triive.cvreader.utils.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : BaseViewHolder<T>>(
    protected val collection: MutableCollection<T> = mutableListOf(),
    private val onClick: ((T, transactionViews: List<View>) -> Unit)? = null
) : RecyclerView.Adapter<VH>() {
    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(collection.elementAt(position), onClick)

    open fun submitList(items: Collection<T>) {
        collection.addAll(items)
        notifyDataSetChanged()
    }
}
