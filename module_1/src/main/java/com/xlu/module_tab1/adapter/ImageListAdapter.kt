package com.xlu.module_tab1.adapter

import coil.load
import com.example.baselibrary.databinding.ListItemBinding
import com.example.baselibrary.recyclerview.BaseBRVAdapter
import com.xlu.module_tab1.R
import com.xlu.module_tab1.bean.Image
import com.xlu.module_tab1.databinding.FragmentCoilItemBinding
import com.xlu.module_tab1.ui.FragmentCoil


class ImageListAdapter : BaseBRVAdapter<Image, FragmentCoilItemBinding>(FragmentCoilItemBinding::inflate) {


    override fun convert(holder: BaseBindingHolder, item: Image) {
        val binding = holder.getViewBinding<FragmentCoilItemBinding>()
        binding.apply {
            image.load(item.url){
                placeholder(R.drawable.icon_placeholdder)
                error(R.drawable.icon_errorload)
            }
        }
    }

}