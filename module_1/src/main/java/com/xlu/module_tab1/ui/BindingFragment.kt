package com.xlu.module_tab1.ui

import androidx.fragment.app.activityViewModels
import com.example.baselibrary.base_databinding.BaseBindingFragment
import com.xlu.module_tab1.HomeViewModel
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentDatabindBinding

class BindingFragment : BaseBindingFragment<FragmentDatabindBinding>(R.layout.fragment_databind) {

    private val viewmodel by activityViewModels<HomeViewModel>()

    override suspend fun initData() {
        //不要忘了赋值
        mBinding.viewmodel = viewmodel

        viewmodel.add()
    }

}