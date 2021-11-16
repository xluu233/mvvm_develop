package com.xlu.module_tab1.adapter

import com.example.baselibrary.recyclerview.BaseAdapter
import com.xlu.module_tab1.databinding.FragmentBrvaBinding


class QuickBiningAdapter : BaseAdapter<Book,FragmentBrvaBinding>(FragmentBrvaBinding::inflate) {


    override fun convert(holder: BaseBindingHolder, item: Book) {
        val binding = holder.getViewBinding<FragmentBrvaBinding>()
        binding.itemNumber.text = item.id.toString()
        binding.content.text = item.name
    }

}