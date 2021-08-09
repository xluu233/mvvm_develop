package com.example.mvvm_develop.ui.tab1

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.common.initFragment
import com.example.baselibrary.delegate.viewBinding
import com.example.baselibrary.log.xLog
import com.example.mvvm_develop.CommonViewModel
import com.example.mvvm_develop.R
import com.example.mvvm_develop.databinding.FragmentTab1Binding
import com.google.android.material.tabs.TabLayoutMediator

class FragmentTab1 : BaseFragment(R.layout.fragment_tab1) {

    companion object{
        @JvmStatic
        fun newInstance(): Fragment = FragmentTab1()
    }

    private val binding by viewBinding(FragmentTab1Binding::bind)

    private val commonViewModel: CommonViewModel by activityViewModels<CommonViewModel>()

    override fun initData() {
/*        binding.viewPager.initFragment(this, listOf<Fragment>(FragmentTab1(0), FragmentTab1(1)))

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()*/

        commonViewModel.load()

        commonViewModel.liveData.observe(viewLifecycleOwner, Observer {
            it.data?.forEach {
                xLog.d("BannerData",it.url)
            }
        })

    }

}