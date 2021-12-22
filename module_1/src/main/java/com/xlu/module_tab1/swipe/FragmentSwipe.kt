package com.xlu.module_tab1.swipe

import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.viewbinding.viewBinding
import com.xlu.common.hideBottomNav
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentSwipeBinding

/**
 * TODO 一个可以侧拉关闭的界面
 */
class FragmentSwipe : BaseFragment(R.layout.fragment_swipe) {

    private val binding by viewBinding(FragmentSwipeBinding::bind)

    override suspend fun initData() {

        binding.openActivity.click {
            SwipeBackTestActivity.openSwipeTestActivity()
        }

    }


    override fun onDestroyView() {
        hideBottomNav(false)
        super.onDestroyView()
    }
}