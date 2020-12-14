package com.victorgomes.test.myapplication.view.productlist

import com.victorgomes.test.myapplication.R
import com.victorgomes.test.myapplication.databinding.ItemProductBinding
import com.victorgomes.test.myapplication.domain.model.ProductItemListDomain
import com.victorgomes.test.myapplication.view.util.CommomListAdapter


class ListAdapter(values: List<ProductItemListDomain>) :
    CommomListAdapter<ProductItemListDomain, ItemProductBinding>(values) {

    override fun getItemLayout(viewType: Int): Int = R.layout.item_product

    inner class MViewHolder(private val itemBinding: ItemProductBinding) :
        BaseViewHolder(itemBinding) {
        override fun bind(item: ProductItemListDomain) {
            itemBinding.product = item
            itemBinding.root.setOnClickListener {
                itemClickListener?.invoke(it, adapterPosition, getItem(position = adapterPosition))
            }
        }
    }

    override fun createViewHolder(viewType: Int, itemBinding: ItemProductBinding): BaseViewHolder =
        MViewHolder(itemBinding)

}