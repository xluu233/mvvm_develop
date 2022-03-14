package com.example.mvvm_develop.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseActivity
import com.example.baselibrary.bus.LiveDataBus
import com.example.baselibrary.viewbinding.viewBinding
import com.example.baselibrary.navigation.NavHostFragment
import com.example.baselibrary.utils.activity.lifecycleOwner
import com.example.mvvm_develop.vm.CommonViewModel
import com.example.mvvm_develop.R
import com.example.mvvm_develop.databinding.ActivityMainBinding
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantEvent
import com.xlu.common.constants.ConstantParams


@Route(path = ConstantARouter.MainActivity)
class MainActivity : BaseActivity(R.layout.activity_main){

    //viewBinding创建方法见：
    //https://juejin.cn/post/6960914424865488932
    private val binding by viewBinding(ActivityMainBinding::bind)

    //此处viewMode创建方法见：
    //https://developer.android.com/topic/libraries/architecture/viewmodel?hl=zh-cn
    private val viewModel by viewModels<CommonViewModel>()

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override suspend fun initData(savedInstanceState: Bundle?) {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        navController.navigate(R.id.mainFragment)
        initObserver()
    }

    private fun initObserver() {

        LiveDataBus.with<String>(ConstantEvent.NAVIGATION_FRAGMENT_EVENT).observe(lifecycleOwner, Observer {
            when(it){
                ConstantEvent.FRAGMENT_NET -> navController.navigate(R.id.action_mainFragment_to_fragmentNet2)
                ConstantEvent.FRAGMENT_ROOM -> navController.navigate(R.id.action_mainFragment_to_fragmentRoom)
                ConstantEvent.FRAGMENT_MMKV -> navController.navigate(R.id.action_mainFragment_to_fragmentMMKV)
                ConstantEvent.FRAGMENT_DATABIND -> navController.navigate(R.id.action_mainFragment_to_dataBindingFragment)
                ConstantEvent.FRAGMENT_RECYCLERVIEW -> navController.navigate(R.id.action_mainFragment_to_brvaFragment)
                ConstantEvent.COMMUNITY_FRAGMENT -> {
                    ARouter.getInstance().build(ConstantARouter.CommunityActivity).navigation(this)
                }
                else -> {

                }
            }
        })
    }


}