package com.xlu.module_tab1.ui

import androidx.fragment.app.activityViewModels
import com.example.baselibrary.base_databinding.DataBindingBaseFragment
import com.xlu.module_tab1.HomeViewModel
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentDatabindBinding

class DataBindingFragment : DataBindingBaseFragment<FragmentDatabindBinding>(R.layout.fragment_databind) {

    private val viewmodel by activityViewModels<HomeViewModel>()

    override fun initData() {
        //不要忘了赋值
        mBinding.viewmodel = viewmodel

        viewmodel.add()
    }

}