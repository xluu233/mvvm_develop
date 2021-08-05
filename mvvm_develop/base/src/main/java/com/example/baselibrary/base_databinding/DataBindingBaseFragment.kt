package com.example.baselibrary.base_databinding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.baselibrary.R
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.databinding.BaseFragmentLayoutBinding
import com.example.baselibrary.navigation.NavHostFragment

abstract class DataBindingBaseFragment<T : ViewDataBinding>(@LayoutRes private val layout: Int) : BaseFragment(layout) {

    private var _mBinding: T? = null
    private lateinit var mBaseContainBinding: BaseFragmentLayoutBinding
    val mBinding get() = _mBinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBaseContainBinding = DataBindingUtil.inflate(inflater, R.layout.base_fragment_layout, container, false)
        _mBinding = DataBindingUtil.inflate(inflater, layout, container, false)

        mBaseContainBinding.baseContainer.addView(_mBinding?.root)
        return mBaseContainBinding.root
    }




}