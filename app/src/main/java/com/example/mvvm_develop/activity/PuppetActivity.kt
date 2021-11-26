package com.example.mvvm_develop.activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseActivity
import com.example.baselibrary.navigation.NavHostFragment
import com.example.baselibrary.utils.log.xLog
import com.example.baselibrary.viewbinding.viewBinding
import com.example.mvvm_develop.R
import com.example.mvvm_develop.databinding.ActivityMainBinding
import com.example.mvvm_develop.databinding.ActivityTestBinding
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantParams


/**
 * TODO Puppet:傀儡,玩具
 * 用来当做页面跳转容器
 */
@Route(path = ConstantARouter.PoppetActivity)
class PuppetActivity : BaseActivity(R.layout.activity_test) {

    private val binding by viewBinding(ActivityTestBinding::bind)

    @Autowired(name = ConstantParams.NavigationDestination)
    private var navigationDestination:String ?= null

    private lateinit var navController: NavController

    override suspend fun initData(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        val navHostFragment = binding.poppetFragmentContainer as NavHostFragment
        navController = navHostFragment.navController

        initPage()
    }

    private fun initPage() {
        if (navigationDestination.isNullOrEmpty()) {
            xLog.e(TAG, "fragment path is null, return")
            finish()
            return
        }

        var fragment = supportFragmentManager.findFragmentByTag(navigationDestination)
        if (fragment == null) {
            fragment = ARouter.getInstance().build(navigationDestination).navigation() as Fragment?
            if (fragment == null) {
                xLog.e(TAG, "no fragment from $navigationDestination")
                finish()
                return
            }
            copyExtras2Fragment(fragment)

            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.mainFragment, fragment, navigationDestination)
            fragmentTransaction.commitAllowingStateLoss()
        }


    }

    private fun copyExtras2Fragment(fragment: Fragment) {
        var arguments = fragment.arguments
        if (arguments == null) {
            arguments = Bundle()
        }
        intent.extras?.run {
            arguments.putAll(this)
        }
        fragment.arguments = arguments
    }


}