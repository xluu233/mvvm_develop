package com.example.module_community.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.example.baselibrary.recyclerview.BaseBRVAdapter
import com.example.baselibrary.utils.activity.application
import com.example.baselibrary.utils.other.screenWidth
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.utils.view.dp
import com.example.module_community.R
import com.example.module_community.bean.Image
import com.example.module_community.databinding.FragmentCoilItemBinding
import com.jakewharton.rxbinding4.view.longClicks


val imageLoader by lazy {
    ImageLoader.Builder(application)
        .availableMemoryPercentage(0.5)
        .crossfade(true)
        .placeholder(R.color.grey)
        .error(R.color.red)
        .bitmapConfig(Bitmap.Config.ARGB_8888)
        .build()
}

val imageWidth: Int
    get() = ((screenWidth - (3.dp)*2 )/3).toInt()


/**
 * 垃圾Coil
 */
class ImageListAdapter : BaseBRVAdapter<Image, FragmentCoilItemBinding>(FragmentCoilItemBinding::inflate) {


    override fun onItemViewHolderCreated(viewHolder: BaseBindingHolder, viewType: Int) {
        val binding = viewHolder.getViewBinding<FragmentCoilItemBinding>()
        binding.image.apply {
            layoutParams.width = imageWidth
            layoutParams.height = imageWidth
        }
        super.onItemViewHolderCreated(viewHolder, viewType)
    }


    @SuppressLint("CheckResult")
    override fun convert(holder: BaseBindingHolder, item: Image) {
        val binding = holder.getViewBinding<FragmentCoilItemBinding>()
        binding.apply {
            //image.load(item.url,imageLoader)
            title.text = item.id.toString()
            image.click {
                listener?.click(holder.absoluteAdapterPosition,holder.itemView,item)
            }
            image.setOnLongClickListener {
                listener?.longClick(holder.absoluteAdapterPosition,holder.itemView,item)
                true
            }
        }

        val request = ImageRequest.Builder(context)
            .data(item.url)//图片地址
            .crossfade(true)
            .memoryCachePolicy(CachePolicy.ENABLED)//设置内存的缓存策略
            .diskCachePolicy(CachePolicy.ENABLED)//设置磁盘的缓存策略
            .networkCachePolicy(CachePolicy.ENABLED)//设置网络的缓存策略
            .target { drawable -> //图片加载之后的处理
                //处理逻辑
//                val bitmap = drawable.toBitmap()
//                val height = (screenWidth/bitmap.width)*bitmap.height
//                Log.d("TAG", "convert: screenWidth:$screenWidth,width:${bitmap.width},height:${bitmap.height}   -$height")

                binding.image.apply {
                    this.layoutParams.height = this.width
                    setImageDrawable(drawable)
                }
            }
            .build()
        imageLoader.enqueue(request)
    }

}