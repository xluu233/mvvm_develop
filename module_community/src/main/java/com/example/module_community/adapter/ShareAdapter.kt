package com.example.module_community.adapter

import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.example.baselibrary.recyclerview.BaseBRVAdapter
import com.example.baselibrary.recyclerview.BaseDataBindAdapter
import com.example.baselibrary.recyclerview.GridSpaceItemDecoration
import com.example.baselibrary.utils.view.dp
import com.example.module_community.R
import com.example.module_community.bean.Community
import com.example.module_community.databinding.Item1Binding
import com.example.module_community.databinding.NineGridImageBinding

/**
 * @ClassName AdapterDemo4
 * @Description TODO 使用RecyclerView实现九宫格，刷新有问题，等待修复
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/16 14:01
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

class ShareAdapter : BaseDataBindAdapter<Community, Item1Binding>(R.layout.item_1) {


    override fun onItemViewHolderCreated(viewHolder: BaseDataBindingHolder<Item1Binding>, viewType: Int) {
        viewHolder.dataBinding?.imageList?.apply {
            adapter = NineGridImageAdapter()
        }
        super.onItemViewHolderCreated(viewHolder, viewType)
    }

    override fun convert(holder: BaseDataBindingHolder<Item1Binding>, item: Community) {
        val bind = holder.dataBinding
        bind?.data = item
        bind?.imageList?.apply {
            val spanCount = when(item.img.size){
                in 0..1 -> 1
                in 2..4 -> 2
                else -> 3
            }
            val gridLayoutManager = GridLayoutManager(context,spanCount)
            val decoration = when(spanCount){
                in 0..1 -> 5.dp.toInt()
                in 2..2 -> 3.dp.toInt()
                else -> 2.dp.toInt()
            }
            layoutManager = gridLayoutManager
            addItemDecoration(GridSpaceItemDecoration(spanCount,decoration,decoration))
            (adapter as NineGridImageAdapter).setList(item.img)
        }

    }

}