package com.example.baselibrary.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


/**
 * @ClassName BaseAdapter
 * @Description 基础Adapter封装，使用ViewBinding
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/16 11:48
 */
abstract class BaseAdapter<T, VB : ViewBinding>(
    private val list: List<T>,
    private val inflate: (LayoutInflater, ViewGroup, Boolean) -> VB,
) : RecyclerView.Adapter<BindingViewHolder<VB>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<VB> {
        return BindingViewHolder<VB>(parent, inflate)
    }

    override fun getItemCount(): Int = list.size
}