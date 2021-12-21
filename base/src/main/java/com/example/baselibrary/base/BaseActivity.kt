package com.example.baselibrary.base

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.drake.statusbar.darkMode
import com.drake.statusbar.immersive
import com.drake.statusbar.statusBarColor
import com.example.baselibrary.R
import com.example.baselibrary.lifecycle.ActivityStack
import com.example.baselibrary.utils.activity.contentView
import com.example.baselibrary.utils.log.xLog
import com.example.baselibrary.utils.other.Color
import com.example.baselibrary.viewbinding.findRootView
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

abstract class BaseActivity(@LayoutRes private val layout: Int ?= null) : AppCompatActivity() {

    val TAG by lazy {
        this.javaClass.simpleName;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout?.let {
            setContentView(it)
        }
        //this.statusBarColor(getColor(R.color.colorPrimary))
        lifecycleScope.launch {
            initStatusBar()
            initData(savedInstanceState)
        }
    }

    abstract suspend fun initData(savedInstanceState: Bundle?=null)


    /**
     * 更新状态栏，背景颜色和字体颜色
     */
    open fun initStatusBar() {
        val background = findRootView(this).background
        if (background is ColorDrawable) {
            val color = background.color
            val isWhite = Color.isWhiteColor(color)
            //requireActivity().immersive(color,darkMode = isWhite)
            this.statusBarColor(background.color)
            ActivityStack.currentActivity?.darkMode(isWhite)
        }
    }

    override fun onDestroy() {
        lifecycleScope.cancel()
        super.onDestroy()
    }

}