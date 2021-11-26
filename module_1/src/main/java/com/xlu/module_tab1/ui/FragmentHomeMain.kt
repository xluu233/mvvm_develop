package com.xlu.module_tab1.ui

import androidx.fragment.app.activityViewModels
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.bus.LiveDataBus
import com.example.baselibrary.viewbinding.viewBinding
import com.example.baselibrary.utils.view.click
import com.xlu.common.constants.ConstantEvent
import com.xlu.common.goLogin
import com.xlu.common.hideBottomNav
import com.xlu.module_tab1.HomeViewModel
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentHomeBinding
import com.xlu.module_tab1.dialog.TestDialog

class FragmentHomeMain : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by activityViewModels()


    override suspend fun initData() {
        initClick()

    }



    private fun initClick() {

        /*---------子module控制app外层跳转-----*/
//        方法一：预定义，通过event事件分发
//        LiveDataBus.with<String>(ConstantEvent.NAVIGATION_FRAGMENT_EVENT).postData(ConstantEvent.FRAGMENT_NET)
//
//        方法二：通过ARouter想外提供的接口实现
//        ServerUtil.getAppServer().navigation(FragmentNet::class.java.name)

        binding.testNet.click {
            LiveDataBus.with<String>(ConstantEvent.NAVIGATION_FRAGMENT_EVENT).postData(ConstantEvent.FRAGMENT_NET)

            //本module跳转
            //navigate(R.id.action_HomeMainFragment_to_fragmentNet)
        }

        binding.testMmkv.click {
            LiveDataBus.with<String>(ConstantEvent.NAVIGATION_FRAGMENT_EVENT).postData(ConstantEvent.FRAGMENT_MMKV)
        }

        binding.testRoom.click {
            LiveDataBus.with<String>(ConstantEvent.NAVIGATION_FRAGMENT_EVENT).postData(ConstantEvent.FRAGMENT_ROOM)
        }

        binding.testDatabind.click {
            LiveDataBus.with<String>(ConstantEvent.NAVIGATION_FRAGMENT_EVENT).postData(ConstantEvent.FRAGMENT_DATABIND)
        }

        binding.goLogin.click {
            goLogin()
        }

        binding.testDialog.click {
            TestDialog.getInstance().show(requireActivity().supportFragmentManager,"test")
        }

        binding.testBrva.click {
            LiveDataBus.with<String>(ConstantEvent.NAVIGATION_FRAGMENT_EVENT).postData(ConstantEvent.FRAGMENT_RECYCLERVIEW)
        }

        //hideBottomNav(true)

        binding.communityModule.click {
            LiveDataBus.with<String>(ConstantEvent.NAVIGATION_FRAGMENT_EVENT).postData(ConstantEvent.COMMUNITY_FRAGMENT)
        }

    }


}