package com.example.baselibrary.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


/**
 * 使用 ViewBinding 和 BaseRecyclerViewAdapter的基础Adapter封装
 */
abstract class BaseBRVAdapter<T, VB : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup, Boolean) -> VB,
    layoutResId: Int = -1
) : BaseQuickAdapter<T, BaseBRVAdapter.BaseBindingHolder>(layoutResId) {

    var listener : AdapterClickListener<T> ?= null

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int) =
        BaseBindingHolder(inflate(LayoutInflater.from(parent.context), parent, false))

    class BaseBindingHolder(private val binding: ViewBinding) : BaseViewHolder(binding.root) {
        constructor(itemView: View) : this(ViewBinding { itemView })

        @Suppress("UNCHECKED_CAST")
        fun <VB : ViewBinding> getViewBinding() = binding as VB
    }

    open fun setClickListener(listener: AdapterClickListener<T>){
        this.listener = listener
    }


}