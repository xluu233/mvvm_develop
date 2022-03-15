package com.xlu.module_ui.ui

import androidx.fragment.app.activityViewModels
import com.example.baselibrary.base_databinding.BaseBindingFragment
import com.xlu.module_ui.HomeViewModel
import com.xlu.module_ui.R
import com.xlu.module_ui.databinding.FragmentDatabindBinding

class BindingFragment : BaseBindingFragment<FragmentDatabindBinding>(R.layout.fragment_databind) {

    private val viewmodel by activityViewModels<HomeViewModel>()

    override suspend fun initData() {
        //不要忘了赋值
        mBinding.viewmodel = viewmodel

        viewmodel.add()
    }

}