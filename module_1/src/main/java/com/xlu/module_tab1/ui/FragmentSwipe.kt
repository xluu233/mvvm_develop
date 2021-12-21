package com.xlu.module_tab1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.viewbinding.viewBinding
import com.xlu.common.hideBottomNav
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentSwipeBinding
import com.xlu.module_tab1.swipe.SwipeBackTestActivity


/**
 * TODO 一个可以侧拉关闭的界面
 */
class FragmentSwipe : BaseFragment(R.layout.fragment_swipe) {

    private val binding by viewBinding(FragmentSwipeBinding::bind)

    override suspend fun initData() {

        binding.openActivity.click {
            SwipeBackTestActivity.openSwipeTestActivity()
        }

        binding.openFragment.click {
            navigate(R.id.action_fragmentSwipe_to_swipeBackFragment)
        }
    }


    override fun onDestroyView() {
        hideBottomNav(false)
        super.onDestroyView()
    }
}