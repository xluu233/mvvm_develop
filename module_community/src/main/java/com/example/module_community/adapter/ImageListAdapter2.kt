package com.example.module_community.adapter


import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.example.baselibrary.recyclerview.BaseDataBindAdapter
import com.example.baselibrary.utils.log.xLog
import com.example.baselibrary.utils.view.click
import com.example.module_community.R
import com.example.module_community.bean.Image
import com.example.module_community.databinding.FragmentCoilItemBinding


class ImageListAdapter2 : BaseDataBindAdapter<Image, FragmentCoilItemBinding>(R.layout.fragment_coil_item) {


    override fun onItemViewHolderCreated(viewHolder: BaseDataBindingHolder<FragmentCoilItemBinding>, viewType: Int) {
        viewHolder.dataBinding?.apply {
            image.apply {
                layoutParams.width = imageWidth
                layoutParams.height = imageWidth
            }
        }
        super.onItemViewHolderCreated(viewHolder, viewType)
    }


    override fun convert(holder: BaseDataBindingHolder<FragmentCoilItemBinding>, item: Image) {
        holder.dataBinding?.apply {
            data = item
            image.click {
                listener?.click(holder.absoluteAdapterPosition,holder.itemView,item)
            }
            image.setOnLongClickListener {
                listener?.longClick(holder.absoluteAdapterPosition,holder.itemView,item)
                true
            }
        }
    }


}