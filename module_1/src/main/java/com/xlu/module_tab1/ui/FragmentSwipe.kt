package com.xlu.module_tab1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.base.BaseFragment
import com.xlu.common.hideBottomNav
import com.xlu.module_tab1.R


/**
 * TODO 一个可以侧拉关闭的界面
 */
class FragmentSwipe : BaseFragment(R.layout.fragment_swipe) {

    override suspend fun initData() {

    }


    override fun onDestroyView() {
        hideBottomNav(false)
        super.onDestroyView()
    }
}