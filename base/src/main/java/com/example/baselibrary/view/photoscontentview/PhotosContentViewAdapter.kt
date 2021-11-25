package com.example.baselibrary.view.photoscontentview

import android.content.Context
import android.widget.ImageView

abstract class PhotosContentViewAdapter<T> {

    abstract fun onDisplayImage(context: Context, imageView: ImageView, data: T)

    open fun onItemImageClick(
        context: Context,
        imageView: ImageView,
        index: Int,
        list: MutableList<T>
    ) {
    }

    open fun onItemImageLongClick(
        context: Context,
        imageView: ImageView,
        index: Int,
        list: MutableList<T>
    ): Boolean {
        return false
    }

    open fun generateImageView(context: Context): ImageView {
        return PhotoImageView(context)
    }
}