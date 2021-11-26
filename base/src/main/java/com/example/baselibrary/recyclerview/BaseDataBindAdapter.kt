package com.example.baselibrary.recyclerview


import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 * 使用 DataBinding 和 BaseRecyclerViewAdapter的基础Adapter封装，建议使用
 */
abstract class BaseDataBindAdapter<T, DB : ViewDataBinding>(@LayoutRes layout: Int) : BaseQuickAdapter<T, BaseDataBindingHolder<DB>>(layout) {

    var listener : AdapterClickListener<T> ?= null

    open fun setClickListener(listener: AdapterClickListener<T>){
        this.listener = listener
    }

}
