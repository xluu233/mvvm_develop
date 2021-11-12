package com.example.baselibrary.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes private val layout: Int ?= null) : AppCompatActivity() {

    val TAG by lazy {
        this.javaClass.name;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout?.let {
            setContentView(it)
        }
        initData(savedInstanceState)
    }

    abstract fun initData(savedInstanceState: Bundle?=null)

}