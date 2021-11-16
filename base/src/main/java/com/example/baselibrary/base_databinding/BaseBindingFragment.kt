package com.example.baselibrary.base_databinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.baselibrary.base.BaseFragment

abstract class BaseBindingFragment<T : ViewDataBinding>(@LayoutRes private val layout: Int, lazyInit:Boolean = false) : BaseFragment(layout = layout,lazyInit = lazyInit) {

    private var _mBinding: T? = null
    val mBinding get() = _mBinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _mBinding = DataBindingUtil.inflate(inflater, layout, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //LiveData needs the lifecycle owner
        mBinding.lifecycleOwner = requireActivity()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding?.unbind()
    }

}