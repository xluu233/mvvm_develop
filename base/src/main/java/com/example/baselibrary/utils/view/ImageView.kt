package com.example.baselibrary.utils.view

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.example.baselibrary.R
import com.example.baselibrary.utils.activity.application
import com.example.baselibrary.utils.transformation.CenterBlurTransformation
import com.example.baselibrary.utils.transformation.GlideRoundTransform

/*--------------加载普通图片----------*/
fun ImageView.loadImage(url: String?= null,uri: Uri?=null,@DrawableRes drawable: Int?=null) {
    val result = ((url ?: uri) ?: drawable) ?: R.drawable.icon_errorload
    Glide.with(context)
        .load(result)
        .placeholder(R.color.grey)
        .error(R.drawable.icon_errorload)
        .centerCrop()
        .transition(withCrossFade())
        .into(this)
}


/*------------------高斯模糊加渐入渐出------------*/
fun ImageView.loadBlurTrans(context: Context, uri: Uri, radius: Int) {
    Glide.with(context)
        .load(uri)
        .thumbnail(0.1f).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .apply(RequestOptions.bitmapTransform(
            CenterBlurTransformation(
                radius,
                8,
                context
            )
        ))
        .transition(withCrossFade(400))
        .into(this)
}

/*------------加载圆形图片-----------*/
fun ImageView.loadCircle(uri: Uri) {
    Glide.with(context)
        .load(uri)
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .into(this)
}

fun ImageView.loadCircle(url:String) {
    Glide.with(context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(this)
}

fun ImageView.loadCircle(@DrawableRes drawable:Int) {
    Glide.with(context)
        .load(drawable)
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .into(this)
}


/*-----------圆角图片------------*/
fun ImageView.loadRadius(url: String, radius: Int) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .skipMemoryCache(false)
        .placeholder(R.drawable.icon_errorload)
        .error(R.drawable.icon_errorload)
        .transition(withCrossFade())
        .transform(
            GlideRoundTransform(
                context,
                radius
            )
        )
        .into(this)
}


/*------------占位图--------------*/
fun ImageView.loadError() {
    Glide.with(context)
        .load(R.drawable.icon_errorload)
        .centerCrop()
        .transition(withCrossFade())
        .transform(
            GlideRoundTransform(
                context,
                10
            )
        )
        .into(this)
}
