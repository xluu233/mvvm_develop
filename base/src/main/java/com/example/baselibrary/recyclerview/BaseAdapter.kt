package com.example.baselibrary.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


abstract class BaseAdapter<T, VB : ViewBinding>(
  private val inflate: (LayoutInflater, ViewGroup, Boolean) -> VB,
  layoutResId: Int = -1
) : BaseQuickAdapter<T, BaseAdapter.BaseBindingHolder>(layoutResId) {

  override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int) = BaseBindingHolder(inflate(LayoutInflater.from(parent.context), parent, false))

  class BaseBindingHolder(private val binding: ViewBinding) : BaseViewHolder(binding.root) {
    constructor(itemView: View) : this(ViewBinding { itemView })

    @Suppress("UNCHECKED_CAST")
    fun <VB : ViewBinding> getViewBinding() = binding as VB
  }

}