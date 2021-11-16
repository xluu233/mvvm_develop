package com.example.baselibrary.recyclerview.test

import com.example.baselibrary.databinding.ListItemBinding
import com.example.baselibrary.recyclerview.BaseBRVAdapter


/**
 * 继承BaseBRVAdapter，简单快捷，建议使用
 */
class AdapterDemo2 : BaseBRVAdapter<Book, ListItemBinding>(ListItemBinding::inflate) {

    override fun convert(holder: BaseBindingHolder, item: Book) {
        val binding = holder.getViewBinding<ListItemBinding>()
        binding.apply {
            textView.text = item.name
            button.text = item.id.toString()
        }
    }

}