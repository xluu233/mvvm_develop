package com.example.mvvm_develop

import android.content.Context
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.delegate.viewBinding
import com.example.baselibrary.navigation.NavHostFragment
import com.example.mvvm_develop.databinding.FragmentMainBinding
import com.google.android.material.navigation.NavigationBarView
import com.xlu.module_center.CenterFragment
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

    override fun initData() {
        initBottomNav()
        initObserver()
    }

    private fun initObserver() {

    }

    private fun initBottomNav() {
        binding.bottomNav.run {
            //取消着色
            itemIconTintList = null
            //初始化
            //selectedItemId = R.id.navi_home
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
                navController.navigate(item.itemId)
                return true
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callBack = object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val currentFragment = navHostFragment.childFragmentManager.primaryNavigationFragment
                Log.d(TAG, "handleOnBackPressed: ${currentFragment?.javaClass?.name}")
                if (currentFragment is FragmentHome || currentFragment is FragmentCollection || currentFragment is CenterFragment){
                    requireActivity().finish()
                }else{
                    return
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }
}