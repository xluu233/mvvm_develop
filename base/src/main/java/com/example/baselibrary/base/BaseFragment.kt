package com.example.baselibrary.base

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.drake.statusbar.darkMode
import com.drake.statusbar.immersive
import com.drake.statusbar.statusBarColor
import com.drake.statusbar.statusPadding
import com.example.baselibrary.R
import com.example.baselibrary.lifecycle.ActivityStack
import com.example.baselibrary.navigation.NavHostFragment
import com.example.baselibrary.utils.other.Color
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * @ClassName BaseFragment
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/26 11:45
 */
abstract class BaseFragment(@LayoutRes private val layout: Int, private val lazyInit:Boolean = false) : Fragment(layout) {

    val TAG by lazy {
        this.javaClass.name;
    }

    private var isLoaded = false
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStatusBar()
        if (!lazyInit){
            lifecycleScope.launch {
                initData()
            }
        }
    }

    /**
     * 更新状态栏，背景颜色和字体颜色
     */
    open fun initStatusBar() {
        val background = view?.background
        if (background is ColorDrawable) {
            val color = background.color
            val isWhite = Color.isWhiteColor(color)
            //requireActivity().immersive(color,darkMode = isWhite)
            this.statusBarColor(background.color)
            ActivityStack.currentActivity?.darkMode(isWhite)
        }
    }


    override fun onResume() {
        super.onResume()
        //Fragment是否可见
        if (!isLoaded && !isHidden && lazyInit) {
            lifecycleScope.launch {
                initData()
            }
            isLoaded = true
        }
    }

    /**
     * 初始化数据
     */
    abstract suspend fun initData()


    /**
     * fragment跳转，防止重复点击崩溃
     */
    fun navigate(destination: Int, bundle: Bundle ?= null) = NavHostFragment.findNavController(this).apply {
        currentDestination?.getAction(destination)?.let {
            navigate(destination,bundle)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        lifecycleScope.cancel()
        isLoaded = false
    }
    
}