package com.example.module_community.adapter

import android.graphics.Bitmap
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.baselibrary.recyclerview.BaseBRVAdapter
import com.example.baselibrary.utils.other.screenWidth
import com.example.module_community.R
import com.example.module_community.bean.Image
import com.example.module_community.databinding.FragmentCoilItemBinding


class ImageListAdapter : BaseBRVAdapter<Image, FragmentCoilItemBinding>(FragmentCoilItemBinding::inflate) {






    private val imageLoader by lazy {
        ImageLoader.Builder(context)
            .availableMemoryPercentage(0.3)
            .crossfade(true)
            .placeholder(R.color.grey)
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .build()
    }


    override fun onItemViewHolderCreated(viewHolder: BaseBindingHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)

        //禁止复用 --- 多图会造成卡顿
        viewHolder.setIsRecyclable(false)

    }


    override fun convert(holder: BaseBindingHolder, item: Image) {
        val binding = holder.getViewBinding<FragmentCoilItemBinding>()
        binding.apply {
            //image.load(item.url,imageLoader)
            title.text = item.id.toString()
        }

        val request = ImageRequest.Builder(context)
            .data(item.url)//图片地址
            .crossfade(true)
            .memoryCachePolicy(CachePolicy.ENABLED)//设置内存的缓存策略
            .diskCachePolicy(CachePolicy.ENABLED)//设置磁盘的缓存策略
            .networkCachePolicy(CachePolicy.ENABLED)//设置网络的缓存策略
            .target { drawable -> //图片加载之后的处理
                //处理逻辑
                val bitmap = drawable.toBitmap()
                val height = (screenWidth/bitmap.width)*bitmap.height

                Log.d("TAG", "convert: screenWidth:$screenWidth,width:${bitmap.width},height:${bitmap.height}   -$height")
                binding.image.apply {
                    this.layoutParams.width = screenWidth
                    this.layoutParams.height = height
                    setImageBitmap(bitmap)
                }
            }
            .build()
        imageLoader.enqueue(request)

    }

}