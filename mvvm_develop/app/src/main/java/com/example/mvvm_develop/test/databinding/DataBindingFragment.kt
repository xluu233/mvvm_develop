package com.example.mvvm_develop.test.databinding

import androidx.fragment.app.activityViewModels
import com.example.baselibrary.base_databinding.DataBindingBaseFragment
import com.example.mvvm_develop.R
import com.example.mvvm_develop.databinding.FragmentDatabindBinding

class DataBindingFragment : DataBindingBaseFragment<FragmentDatabindBinding>(R.layout.fragment_databind) {

    private val viewmodel by activityViewModels<DataBindViewModel>()

    override fun initData() {
        //不要忘了赋值
        mBinding.viewmodel = viewmodel

        viewmodel.add()
    }

}