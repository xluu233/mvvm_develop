package com.example.baselibrary.recyclerview


import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import org.jetbrains.annotations.NotNull


/**
 * 使用 DataBinding 和 BaseRecyclerViewAdapter的基础Adapter封装
 */
abstract class DataBindAdapter<T, DB : ViewDataBinding>(@LayoutRes layout: Int) : BaseQuickAdapter<T, BaseDataBindingHolder<DB>>(layout) {


}
