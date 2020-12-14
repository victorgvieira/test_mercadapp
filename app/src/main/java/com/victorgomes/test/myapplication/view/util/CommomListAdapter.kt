package com.victorgomes.test.myapplication.view.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class CommomListAdapter<T, E : ViewDataBinding>(@NonNull protected var values: List<T>) :
    RecyclerView.Adapter<CommomListAdapter<T, E>.BaseViewHolder>() {
    protected var itemClickListener: ((View, Int, T) -> Unit)? = null

    abstract fun createViewHolder(
        viewType: Int,
        itemBinding: E
    ): BaseViewHolder

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder =
        createViewHolder(
            viewType, DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getItemLayout(viewType), parent, false
            )
        )

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (!values.isNullOrEmpty()) {
            val item = values[position]
            holder.bind(item)
        }
    }

    fun subscribeToItemSelection(listener: (view: View, position: Int, item: T) -> Unit) {
        itemClickListener = listener
    }

    open fun swapData(list: List<T>) {
        values = ArrayList(list)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T = values[position]

    override fun getItemCount(): Int = if (values.isNullOrEmpty()) 0 else values.size

    abstract fun getItemLayout(viewType: Int): Int

    abstract inner class BaseViewHolder(itemBinding: E) :
        RecyclerView.ViewHolder(itemBinding.root) {
        abstract fun bind(item: T)
    }
}