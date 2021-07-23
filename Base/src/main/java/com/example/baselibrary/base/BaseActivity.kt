package com.example.baselibrary.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding>(@LayoutRes private val layout: Int) : AppCompatActivity() {

    private var _mBinding: T ?= null
    val mBinding get() = _mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mBinding = DataBindingUtil.setContentView(this, layout)
        initData(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding?.unbind()
    }

    abstract fun initData(savedInstanceState: Bundle?=null)

}