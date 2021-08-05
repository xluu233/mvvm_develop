package com.example.baselibrary.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
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

    override fun onResume() {
        super.onResume()
        xLog.d(TAG,"onResume")
    }


    abstract fun initData(savedInstanceState: Bundle?=null)

}