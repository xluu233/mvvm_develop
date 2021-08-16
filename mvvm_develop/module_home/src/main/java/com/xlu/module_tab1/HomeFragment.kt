package com.xlu.module_tab1

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.delegate.viewBinding
import com.xlu.common.constants.ConstantARouter
import com.xlu.module_tab1.databinding.FragmentHomeBinding

@Route(path = ConstantARouter.FragmentTab1_Main)
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    companion object{
        @JvmStatic
        fun newInstance(): Fragment = HomeFragment()
    }

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by activityViewModels()


    override fun initData() {


    }

}