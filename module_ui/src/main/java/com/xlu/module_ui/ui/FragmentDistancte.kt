package com.xlu.module_ui.ui

import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.viewbinding.viewBinding
import com.xlu.common.hideBottomNav
import com.xlu.module_ui.R
import com.xlu.module_ui.databinding.FragmentDistancteBinding

class FragmentDistancte : BaseFragment(R.layout.fragment_distancte) {

    private val binding by viewBinding(FragmentDistancteBinding::bind)

    override suspend fun initData() {
//        binding.adjustView.click {
//            xLog.d("distance:${it.height}")
//        }
    }

    override fun onDestroyView() {
        hideBottomNav(false)
        super.onDestroyView()
    }
}