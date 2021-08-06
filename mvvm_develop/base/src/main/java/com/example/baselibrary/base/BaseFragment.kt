package com.example.baselibrary.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.baselibrary.log.xLog
import com.example.baselibrary.navigation.NavHostFragment

/**
 * @ClassName BaseFragment
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/26 11:45
 */
abstract class BaseFragment(@LayoutRes private val layout: Int, private val lazyInit:Boolean = false) : Fragment(layout) {

    private val TAG by lazy {
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
        xLog.d(TAG,"onViewCreated")
        if (!lazyInit){
            initData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        xLog.d(TAG,"onCreate")
    }

    override fun onResume() {
        super.onResume()
        xLog.d(TAG,"onResume")
        //Fragment是否可见
        if (!isLoaded && !isHidden && lazyInit) {
            initData()
            isLoaded = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        xLog.d(TAG,"onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        xLog.d(TAG,"onActivityCreated")
    }


    /**
     * 初始化数据
     */
    abstract fun initData()


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
        isLoaded = false
        xLog.d(TAG,"onDestroyView")
    }
    
}