package com.example.baselibrary.recyclerview.test

import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.baselibrary.R
import com.example.baselibrary.databinding.ListItemBinding
import com.example.baselibrary.recyclerview.getViewBinding
import com.example.baselibrary.recyclerview.withBinding


/**
 * 直接使用BRVA中的BaseQuickAdapter，通过扩展类BindingHolderUtil使用binding
 */
class AdapterDemo1 : BaseQuickAdapter<Book, BaseViewHolder>(R.layout.list_item) {

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return super.onCreateDefViewHolder(parent, viewType).withBinding {
            ListItemBinding.bind(it)
        }
    }

    override fun convert(holder: BaseViewHolder, item: Book) {
        val binding = holder.getViewBinding<ListItemBinding>()
        binding.apply {
            textView.text = item.name
            button.text = item.id.toString()
        }
    }

}