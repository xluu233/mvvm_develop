package com.example.baselibrary.base_databinding

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.baselibrary.base.BaseActivity

abstract class BaseBindingActivity<T : ViewDataBinding>(@LayoutRes private val layout: Int) : BaseActivity() {

    private var _mBinding: T ?= null
    val mBinding get() = _mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mBinding = DataBindingUtil.setContentView(this, layout)
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding?.unbind()
    }


}