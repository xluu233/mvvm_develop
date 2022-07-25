package com.example.mvvm_develop.activity

import android.os.Bundle
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
import com.example.mvvm_develop.databinding.ActivityTestBinding
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantParams


/**
 * TODO 页面跳转容器
 * Puppet:傀儡,玩具
 */
@Route(path = ConstantARouter.PoppetActivity)
class PuppetActivity : BaseActivity(R.layout.activity_test) {

    private val binding by viewBinding(ActivityTestBinding::bind)

    @Autowired(name = ConstantParams.NavigationDestination)
    @JvmField
    var navigationDestination: String? = null

    override suspend fun initData(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        initPage()
    }

    private fun initPage() {
        if (navigationDestination.isNullOrEmpty()) {
            xLog.e(TAG, "fragment path is null, return")
            finish()
        }

        val fragment = supportFragmentManager.findFragmentByTag(navigationDestination)
        if (fragment == null) {
            xLog.e(TAG, "fragment is null, return")
            finish()
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.poppet_fragment, fragment!!, navigationDestination)
        fragmentTransaction.commitAllowingStateLoss()
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