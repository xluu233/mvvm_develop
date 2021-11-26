package com.example.mvvm_develop.ui

import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.drake.statusbar.statusPadding
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.bus.LiveDataBus
import com.example.baselibrary.viewbinding.viewBinding
import com.example.baselibrary.navigation.NavHostFragment
import com.example.baselibrary.utils.view.dp
import com.example.mvvm_develop.R
import com.example.mvvm_develop.databinding.FragmentMainBinding
import com.example.mvvm_develop.vm.CommonViewModel
import com.google.android.material.navigation.NavigationBarView
import com.xlu.common.constants.ConstantEvent
import com.xlu.module_center.FragmentCenter
import com.xlu.module_collection.FragmentCollection
import com.xlu.module_tab1.FragmentHome

/**
 * @ClassName MainFragment
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/16 14:28
 */
class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel : CommonViewModel by activityViewModels()
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    private val fragmentTab1 by lazy {
        FragmentHome.newInstance()
    }

    private val fragmentTab2 by lazy {
        FragmentCollection.newInstance()
    }

    private val fragmentTab3 by lazy {
        FragmentCenter.newInstance()
    }

    override fun initData() {
        initBottomNav()
        initObserver()
    }

    private fun initObserver() {
        LiveDataBus.with<Boolean>(ConstantEvent.HIDE_APP_BOTTOM_NAVIGATION).observe(this, Observer {
            if (it){
                binding.bottomNav.animate().translationY(100.dp).start()
            }else{
                binding.bottomNav.animate().translationY(0f).start()
            }
        })


    }

    private fun initBottomNav() {
        binding.bottomNav.run {
            //取消着色
            itemIconTintList = null
            //去掉长按toast
            val ids = mutableListOf<Int>()
            ids.add(R.id.navi_home)
            ids.add(R.id.navi_collection)
            ids.add(R.id.navi_center)
            val bottomViewGroup : ViewGroup = this.getChildAt(0) as ViewGroup
            for (i in 0 until ids.size){
                bottomViewGroup.getChildAt(i).findViewById<View>(ids[i]).setOnLongClickListener {
                    true
                }
            }

        }

        navHostFragment = childFragmentManager.findFragmentById(R.id.module_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        //另一种绑定方式
        //setupWithNavController(binding.bottomNav,navController)

        binding.bottomNav.setOnItemSelectedListener(object :NavigationBarView.OnItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                //navController.navigate(item.itemId)
                when (item.itemId) {
                    R.id.navi_home -> {
                        switchFragment(fragmentTab1)
                    }
                    R.id.navi_collection -> {
                        switchFragment(fragmentTab2)
                    }
                    R.id.navi_center -> {
                        switchFragment(fragmentTab3)
                    }
                }

                return true
            }
        })
    }

    private var currentFragment: Fragment? = null
    private fun switchFragment(fragment: Fragment){
        parentFragmentManager.beginTransaction().apply {
            if (currentFragment==null){
                add(R.id.home_fragment_container,fragment).show(fragment)
            }else if (currentFragment==fragment){
                return
            }else{
                hide(currentFragment!!)
                if (fragment.isAdded){
                    show(fragment)
                }else{
                    add(R.id.home_fragment_container,fragment).show(fragment)
                }
            }
            currentFragment = fragment
        }.commit()
    }


}