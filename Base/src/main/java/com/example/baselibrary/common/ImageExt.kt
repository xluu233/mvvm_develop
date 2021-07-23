package com.example.baselibrary.common

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
import com.example.baselibrary.view.CenterBlurTransformation
import com.example.baselibrary.view.GlideRoundTransform


/*-----------加载网络图片---------------*/
fun ImageView.loadUrl(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .transition(withCrossFade())
        .into(this)
}

fun ImageView.loadUri(context: Context, uri: Uri) {
    Glide.with(context)
        .load(uri)
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
fun ImageView.loadCircle(context: Context, uri: Uri) {
    Glide.with(context)
        .load(uri)
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .into(this)
}

fun ImageView.loadCircle(context: Context, url:String) {
    Glide.with(context)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(this)
}

fun ImageView.loadCircle(context: Context,@DrawableRes drawable:Int) {
    Glide.with(context)
            .load(context.getDrawable(drawable))
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(this)
}


/*-----------圆角图片------------*/
fun ImageView.loadRadius(context: Context, url: String, radius: Int) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .skipMemoryCache(false)
        .placeholder(R.drawable.icon_errorload)
        .error(R.drawable.icon_errorload)
        .transition(withCrossFade())
        .transform(GlideRoundTransform(context,radius))
        .into(this)
}


/*------------占位图--------------*/
fun ImageView.loadError(context: Context) {
    Glide.with(context)
        .load(R.drawable.icon_errorload)
        .centerCrop()
        .transition(withCrossFade())
        .transform(GlideRoundTransform(context,10))
        .into(this)
}
