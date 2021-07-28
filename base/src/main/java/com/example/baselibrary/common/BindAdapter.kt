package com.example.baselibrary.common

import android.graphics.drawable.Drawable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object BindAdapter {

    /*视图可见性*/
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
    @BindingAdapter(value = ["imgSrc"])
    @JvmStatic
    fun imgSrc(view: ImageView, drawable: Int) {
        view.setImageResource(drawable)
    }

    /**
     * 加载网络图片
     */
    @BindingAdapter(value = ["imageUrl"])
    @JvmStatic
    fun imageUrl(view: ImageView, url: String) {
        view.loadUrl(view.context.applicationContext,url)
    }

    /**
     * 加载圆形图片
     */
    @BindingAdapter(value = ["imageUrlCircle"])
    @JvmStatic
    fun imageUrlCircle(view: ImageView, url: String) {
        view.loadRadius(view.context.applicationContext,url,10)
    }

}