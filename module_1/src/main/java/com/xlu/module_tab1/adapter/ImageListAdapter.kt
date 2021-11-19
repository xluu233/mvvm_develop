package com.xlu.module_tab1.adapter

import coil.load
import coil.transform.CircleCropTransformation
import com.example.baselibrary.utils.view.loadUrl
import com.example.baselibrary.recyclerview.BaseBRVAdapter
import com.xlu.module_tab1.R
import com.xlu.module_tab1.bean.Image
import com.xlu.module_tab1.databinding.FragmentCoilItemBinding


class ImageListAdapter : BaseBRVAdapter<Image, FragmentCoilItemBinding>(FragmentCoilItemBinding::inflate) {


    override fun convert(holder: BaseBindingHolder, item: Image) {
        val binding = holder.getViewBinding<FragmentCoilItemBinding>()
        binding.apply {
            image.load(item.url){
                placeholder(R.drawable.icon_errorload)
                crossfade(true)
            }
            title.text = item.id.toString()
            icon.load(R.drawable.icon_placeholdder){
                transformations(CircleCropTransformation())
            }
            //image.loadUrl(item.url)
        }
    }

}