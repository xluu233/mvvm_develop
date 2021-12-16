package com.example.mvvm_develop.adapter

import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.baselibrary.lifecycle.ActivityStack
import com.example.baselibrary.utils.activity.context
import com.example.module_community.adapter.adapterImageLoader
import com.example.mvvm_develop.R
import com.github.chrisbanes.photoview.PhotoView

/**
 * @ClassName ImagePreviewAdapter
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/26 17:01
 */
class ImagePreviewAdapter(private val list: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val imageView = PhotoView(context)
        imageView.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundColor(context.getColor(R.color.white))
            adjustViewBounds = true
            scaleType = ImageView.ScaleType.FIT_CENTER
        }
        return ImageViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageViewHolder).apply {
            val data = list[position]
            zoomView.apply {
                load(data, adapterImageLoader)
                val transName = data
                tag = transName
                ViewCompat.setTransitionName(this, transName)

                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener{
                    override fun onPreDraw(): Boolean {
                        viewTreeObserver.removeOnPreDrawListener(this);
                        ActivityStack.currentActivity?.startPostponedEnterTransition()  //开启动画
                        return true
                    }
                })

            }

        }
    }

    override fun getItemCount(): Int = list.size

    inner class ImageViewHolder(private val view:View) : RecyclerView.ViewHolder(view){
        var zoomView = view as PhotoView
    }

}