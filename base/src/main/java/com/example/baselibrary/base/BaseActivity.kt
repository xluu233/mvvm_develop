package com.example.baselibrary.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.drake.statusbar.immersive
import com.drake.statusbar.statusBarColor
import com.example.baselibrary.R
import com.example.baselibrary.utils.activity.contentView

abstract class BaseActivity(@LayoutRes private val layout: Int ?= null) : AppCompatActivity() {

    val TAG by lazy {
        this.javaClass.name;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout?.let {
            setContentView(it)
        }
        this.statusBarColor(getColor(R.color.colorPrimary))
        initData(savedInstanceState)
    }

    abstract fun initData(savedInstanceState: Bundle?=null)

}