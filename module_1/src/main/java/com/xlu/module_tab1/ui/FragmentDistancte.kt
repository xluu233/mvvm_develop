package com.xlu.module_tab1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.utils.log.xLog
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.viewbinding.viewBinding
import com.xlu.common.hideBottomNav
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentDatabindBinding
import com.xlu.module_tab1.databinding.FragmentDistancteBinding

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