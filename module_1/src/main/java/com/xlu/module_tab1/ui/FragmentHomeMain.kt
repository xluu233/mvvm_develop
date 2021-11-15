package com.xlu.module_tab1.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.bus.LiveDataBus
import com.example.baselibrary.delegate.viewBinding
import com.example.baselibrary.utils.log.xLog
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.utils.view.setNoRepeatClick
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantEvent
import com.xlu.common.goLogin
import com.xlu.common.server.ServerUtil
import com.xlu.module_tab1.HomeViewModel
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentHomeBinding
import com.xlu.module_tab1.dialog.TestDialog

class FragmentHomeMain : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by activityViewModels()



    override fun initData() {

        initClick()

//        LiveDataBus.with<String>("test").observeStick(this, Observer {
//            xLog.d(it)
//        })
//        LiveDataBus.with<String>("test").postStickData("ahahahhah")

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

    }


}