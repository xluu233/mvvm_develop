package com.example.mvvm_develop

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.baselibrary.base.BaseActivity
import com.example.baselibrary.delegate.viewBinding
import com.example.mvvm_develop.databinding.ActivityMainBinding

class MainActivity : BaseActivity(R.layout.activity_main){

    //viewbinding创建方法见：
    //https://juejin.cn/post/6960914424865488932
    private val binding by viewBinding(ActivityMainBinding::bind)

    //此处viewmode创建方法见：
    //https://developer.android.com/topic/libraries/architecture/viewmodel?hl=zh-cn
    private val viewmodel by viewModels<CommonViewModel>()


    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun onBackPressed() {
        //获取hostFragment
        val mMainNavFragment: Fragment? = supportFragmentManager.findFragmentById(R.id.navigation_main)
        //获取当前所在的fragment
        val fragment = mMainNavFragment?.childFragmentManager?.primaryNavigationFragment
        //如果当前处于根fragment即HostFragment
        if (fragment is MainFragment) {
            this.finish()
        }else{
            super.onBackPressed()
        }
    }


}