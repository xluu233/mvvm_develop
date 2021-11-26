package com.example.baselibrary.recyclerview.test

import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.example.baselibrary.R
import com.example.baselibrary.databinding.ListItemBinding
import com.example.baselibrary.recyclerview.BaseDataBindAdapter

/**
 * @ClassName AdapterDemo4
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/16 14:01
 */
class AdapterDemo4 : BaseDataBindAdapter<Book,ListItemBinding>(R.layout.list_item) {

    override fun convert(holder: BaseDataBindingHolder<ListItemBinding>, item: Book) {
        val binding = holder.dataBinding
        binding?.data = item
    }

}