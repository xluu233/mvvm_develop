package com.example.baselibrary.utils.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.baselibrary.common.loadRadius
import com.example.baselibrary.common.loadUrl


object BindAdapter {

    @BindingAdapter(value = ["isGone"])
    @JvmStatic
    fun isGone(view: View, visible: Boolean) {
        //true 或者 1 可见
        //false 0 不可见
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @BindingAdapter(value = ["isGoneByInt"])
    @JvmStatic
    fun isGoneByInt(view: View, visible: Int) {
        //true 或者 1 可见
        //false 0 不可见
        view.visibility = if (visible == 1) View.VISIBLE else View.GONE
    }

    /**
     * 加载资源图片
     */
    @BindingAdapter(value = ["srcImage"])
    @JvmStatic
    fun srcImage(view: ImageView, drawable: Int) {
        view.setImageResource(drawable)
    }

    /**
     * 加载网络图片
     */
    @BindingAdapter(value = ["urlImage"])
    @JvmStatic
    fun urlImage(view: ImageView, url: String) {
        view.loadUrl(view.context.applicationContext,url)
    }

    /**
     * 加载圆形图片
     */
    @BindingAdapter(value = ["circleImage"])
    @JvmStatic
    fun circleImage(view: ImageView, url: String) {
        view.loadRadius(view.context.applicationContext,url,10)
    }

}