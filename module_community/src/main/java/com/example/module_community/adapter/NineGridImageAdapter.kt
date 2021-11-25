package com.example.module_community.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.baselibrary.recyclerview.BaseBRVAdapter
import com.example.module_community.databinding.NineGridImageBinding


/**
 * 继承BaseBRVAdapter，简单快捷，建议使用
 */
class NineGridImageAdapter : BaseBRVAdapter<String, NineGridImageBinding>(NineGridImageBinding::inflate) {

    override fun convert(holder: BaseBindingHolder, item: String) {
        val binding = holder.getViewBinding<NineGridImageBinding>()
        binding.nineGridView.apply {
            Glide.with(context)
                .load(item)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this)
        }
    }

}