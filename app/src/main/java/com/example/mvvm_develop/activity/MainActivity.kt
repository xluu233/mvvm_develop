package com.example.mvvm_develop.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselibrary.base.BaseActivity
import com.example.baselibrary.bus.LiveDataBus
import com.example.baselibrary.delegate.viewBinding
import com.example.baselibrary.navigation.NavHostFragment
import com.example.mvvm_develop.CommonViewModel
import com.example.mvvm_develop.R
import com.example.mvvm_develop.databinding.ActivityMainBinding
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantEvent


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

    override fun initData(savedInstanceState: Bundle?) {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        initObserver()
    }

    private fun initObserver() {

        LiveDataBus.with<Boolean>(ConstantEvent.app_go_databinding_fragment).observe(this, Observer {
            navController.navigate(R.id.action_mainFragment_to_dataBindingFragment)
        })

        viewModel.load()
        
        
        viewModel.liveData.observe(this, Observer {
            Log.d(TAG, "initObserver: ${it.data()}")
        })


        startActivity(Intent(this,TestActivity::class.java))
    }


}