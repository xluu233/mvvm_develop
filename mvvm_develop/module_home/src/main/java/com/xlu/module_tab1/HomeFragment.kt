package com.xlu.module_tab1

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.common.setNoRepeatClick
import com.example.baselibrary.delegate.viewBinding
import com.xlu.common.api.AppItf
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantARouter.ARouterFragment
import com.xlu.common.server.ServerUtil.getAppServer
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

        initClick()

    }

    private fun initClick() {
        setNoRepeatClick(views = arrayOf(binding.button,binding.button2),onClick = {
            when(it){
                binding.button -> {
                    //navigate(R.id.action_mainFragment_to_dataBindingFragment)
                    getAppServer().navigation(ConstantARouter.ARouterFragment)
                }
                binding.button2 -> {
                    //toast(JitpackTest.getTime())
                    ARouter.getInstance().build(ConstantARouter.MainActivity).navigation()
                }

            }
        })
    }

}