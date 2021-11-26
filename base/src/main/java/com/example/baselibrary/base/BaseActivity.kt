package com.example.baselibrary.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.drake.statusbar.immersive
import com.drake.statusbar.statusBarColor
import com.example.baselibrary.R
import com.example.baselibrary.utils.activity.contentView
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            initData(savedInstanceState)
        }
    }

    abstract suspend fun initData(savedInstanceState: Bundle?=null)


    override fun onDestroy() {
        lifecycleScope.cancel()
        super.onDestroy()
    }

}