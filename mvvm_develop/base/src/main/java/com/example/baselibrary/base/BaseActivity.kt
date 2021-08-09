package com.example.baselibrary.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.log.xLog

abstract class BaseActivity(@LayoutRes private val layout: Int ?= null) : AppCompatActivity() {

    val TAG by lazy {
        this.javaClass.name;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        xLog.d(TAG,"onCreate")
        layout?.let {
            setContentView(it)
        }
        initData(savedInstanceState)
    }

    abstract fun initData(savedInstanceState: Bundle?=null)

}