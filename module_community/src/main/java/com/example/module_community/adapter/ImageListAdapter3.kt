package com.example.module_community.adapter

import android.view.ViewGroup
import coil.load
import com.example.baselibrary.recyclerview.BaseAdapter
import com.example.baselibrary.recyclerview.BindingViewHolder
import com.example.baselibrary.utils.log.xLog
import com.example.module_community.bean.Image
import com.example.module_community.databinding.FragmentCoilItemBinding


class ImageListAdapter3(private val list: List<Image>) : BaseAdapter<Image, FragmentCoilItemBinding>(list, FragmentCoilItemBinding::inflate) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<FragmentCoilItemBinding> {
        val viewHolder = BindingViewHolder(parent, FragmentCoilItemBinding::inflate)
        viewHolder.binding.apply {
            image.apply {
                //在绑定数据之前width和height、measureHeight、measureWidth为0 ，在这里设置并没有卵用
                //这里采用手动计算宽度
                layoutParams.width = imageWidth
                layoutParams.height = imageWidth
                xLog.d("onCreateViewHolder:ImageListAdapter3   image: ${this.width} ${this.height}")
            }
        }

        return viewHolder
    }


    override fun onBindViewHolder(holder: BindingViewHolder<FragmentCoilItemBinding>, position: Int) {
        val binding = holder.binding
        val data = list[position]
        binding.apply {
            title.text = data.id.toString()
            image.load(data.url,adapterImageLoader)
        }

        xLog.d("onCreateViewHolder: ${holder.absoluteAdapterPosition},${holder.bindingAdapterPosition},${holder.layoutPosition},${holder.oldPosition}")
    }

}