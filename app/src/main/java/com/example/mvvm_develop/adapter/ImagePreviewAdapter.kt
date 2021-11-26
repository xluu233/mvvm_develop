package com.example.mvvm_develop.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.baselibrary.utils.activity.context
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.view.ZoomImageView
import com.example.module_community.adapter.imageLoader

/**
 * @ClassName ImagePreviewAdapter
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/26 17:01
 */
class ImagePreviewAdapter(private val list: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val imageView = ZoomImageView(context)
        imageView.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            fitsSystemWindows = true
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        return ImageViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageViewHolder).apply {
            zoomView.load(list[position], imageLoader)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class ImageViewHolder(private val view:View) : RecyclerView.ViewHolder(view){
        var zoomView:ZoomImageView = view as ZoomImageView
    }

}