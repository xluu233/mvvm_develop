package com.example.module_community.adapter

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.example.baselibrary.recyclerview.DataBindAdapter
import com.example.baselibrary.utils.design.AppCompat
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.view.photoscontentview.PhotosContentViewAdapter
import com.example.module_community.R
import com.example.module_community.bean.Community
import com.example.module_community.databinding.Item2Binding
import com.example.xlulibrary.ToastBox
import com.example.xlulibrary.toast.xToast

/**
 * @ClassName AdapterDemo4
 * @Description TODO 使用自定义View实现九宫格
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/16 14:01
 */
class ShareAdapter2 : DataBindAdapter<Community, Item2Binding>(R.layout.item_2) {

    override fun onItemViewHolderCreated(viewHolder: BaseDataBindingHolder<Item2Binding>, viewType: Int) {
        viewHolder.dataBinding?.apply {
            imageList.apply {
                setAdapter(object : PhotosContentViewAdapter<String>() {
                    override fun onDisplayImage(context: Context, imageView: ImageView, data: String) {
                        Glide.with(context)
                            .load(data)
                            .apply(
                                RequestOptions().transform(
                                    MultiTransformation(CenterCrop(), RoundedCorners(10))
                                ))
                            .into(imageView)
                    }

                    override fun onItemImageClick(context: Context, imageView: ImageView, index: Int, list: MutableList<String>) {

                    }

                })
            }
            addFocus.apply {
                isActivated = true
                setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.icon_focus))

                click {
                    if (it.isActivated){
                        it.isActivated = false
                        it.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.icon_focused))
                        ToastBox().show("已关注")
                    }else{
                        it.isActivated = true
                        it.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.icon_focus))
                        ToastBox().show("取消关注")
                    }
                }
            }

        }
    }


    override fun convert(holder: BaseDataBindingHolder<Item2Binding>, item: Community) {
        val bind = holder.dataBinding
        bind?.data = item
        bind?.imageList?.setImageData(item.img)
    }

}