package com.xlu.module_ui.adapter

import com.example.baselibrary.recyclerview.BaseAdapter
import com.example.baselibrary.recyclerview.BindingViewHolder
import com.example.baselibrary.utils.log.xLog
import com.xlu.module_ui.databinding.ItemVpBinding

/**
 * @ClassName CardAdapter
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/12/18 21:40
 */
class CardAdapter(private val colors:List<Int>) : BaseAdapter<Int,ItemVpBinding>(colors,ItemVpBinding::inflate) {


    override fun onBindViewHolder(holder: BindingViewHolder<ItemVpBinding>, position: Int) {
        xLog.d("vp position:$position")
        holder.binding.apply {
            vpItemTitle.text = "tab $position"
            root.setBackgroundColor(colors[position])
        }
    }


}