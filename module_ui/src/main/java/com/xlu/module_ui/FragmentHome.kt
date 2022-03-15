package com.xlu.module_ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.viewbinding.viewBinding
import com.xlu.common.constants.ConstantARouter
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentContainerBinding

@Route(path = ConstantARouter.FragmentTab1_Main)
class FragmentHome : BaseFragment(R.layout.fragment_container) {

    companion object{
        @JvmStatic
        fun newInstance(): Fragment = FragmentHome()
    }

    private val binding by viewBinding(FragmentContainerBinding::bind)
    private val viewModel: HomeViewModel by activityViewModels()

    override suspend fun initData() {

    }

}