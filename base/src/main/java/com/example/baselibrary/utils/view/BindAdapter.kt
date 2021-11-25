package com.example.baselibrary.utils.view

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.load


object BindAdapter {

    @BindingAdapter(value = ["isGone"])
    @JvmStatic
    fun isGone(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }


//
//    /**
//     * 加载资源图片
//     */
//    @BindingAdapter(value = ["srcImage"])
//    @JvmStatic
//    fun srcImage(view: ImageView, drawable: Int) {
//        view.setImageResource(drawable)
//    }
//
//    /**
//     * 加载网络图片
//     */
//    @BindingAdapter(value = ["urlImage"])
//    @JvmStatic
//    fun urlImage(view: ImageView, url: String) {
//        view.load(url = url)
//    }

    /**
     * 加载图片
     */
    @BindingAdapter(value = ["loadImage"])
    @JvmStatic
    fun loadImage(view: ImageView, url: String ?= null){
        url?.let {
            view.loadImage(url = it)
        }
    }

    @BindingAdapter(value = ["loadImage1"])
    @JvmStatic
    fun loadImage(view: ImageView, @DrawableRes drawable: Int?=null){
        drawable?.let {
            view.loadImage(drawable = it)
        }
    }

    /**
     * 加载圆形图片
     */
    @BindingAdapter(value = ["circleImage"])
    @JvmStatic
    fun circleImage(view: ImageView, url: String ?= null) {
        url?.let {
            view.loadCircle(it)
        }
    }

    @BindingAdapter(value = ["circleImage1"])
    @JvmStatic
    fun circleImage(view: ImageView,@DrawableRes drawable: Int?=null) {
        drawable?.let {
            view.loadCircle(it)
        }
    }

}