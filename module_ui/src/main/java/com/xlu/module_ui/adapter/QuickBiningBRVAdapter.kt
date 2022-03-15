package com.xlu.module_ui.adapter

import com.example.baselibrary.recyclerview.BaseBRVAdapter
import com.example.baselibrary.recyclerview.test.Book
import com.xlu.module_ui.databinding.FragmentBrvaBinding


class QuickBiningBRVAdapter : BaseBRVAdapter<Book,FragmentBrvaBinding>(FragmentBrvaBinding::inflate) {


    override fun convert(holder: BaseBindingHolder, item: Book) {
        val binding = holder.getViewBinding<FragmentBrvaBinding>()
        binding.itemNumber.text = item.id.toString()
        binding.content.text = item.name
    }

}