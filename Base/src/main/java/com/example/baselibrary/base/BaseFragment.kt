package com.example.baselibrary.base

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
import com.example.baselibrary.databinding.BaseFragmentLayoutBinding
import com.example.baselibrary.navigation.NavHostFragment

abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes private val layout: Int) : Fragment() {

    private var _mBinding: T? = null
    private lateinit var mBaseContainBinding: BaseFragmentLayoutBinding

    lateinit var mContext: Context
    lateinit var mActivity: FragmentActivity

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


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as FragmentActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
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

}