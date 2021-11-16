package com.example.baselibrary.recyclerview.test

import com.example.baselibrary.databinding.ListItemBinding
import com.example.baselibrary.recyclerview.BaseAdapter
import com.example.baselibrary.recyclerview.BindingViewHolder


/**
 * 自定义ReycllerView.Adapter,对于简单列表也可以适用
 */
class AdapterDemo3(private val list: List<Book>) : BaseAdapter<Book,ListItemBinding>(list,ListItemBinding::inflate) {

    override fun onBindViewHolder(holder: BindingViewHolder<ListItemBinding>, position: Int) {
        val binding = holder.binding
        val data = list[position]
        binding.apply {
            textView.text = data.name
            button.text = data.id.toString()
        }
    }

}