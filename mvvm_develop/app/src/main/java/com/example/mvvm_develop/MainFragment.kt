package com.example.mvvm_develop

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.ui.setupWithNavController
import com.example.baselibrary.base.BaseFragment
//import com.example.baselibrary.common.setupWithNavController
import com.example.baselibrary.delegate.viewBinding
import com.example.baselibrary.navigation.NavHostFragment
import com.example.mvvm_develop.databinding.FragmentMainBinding

/**
 * @ClassName MainFragment
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/16 14:28
 */
class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel : CommonViewModel by activityViewModels()

    override fun initData() {
        initBottomNav()
    }


    private fun initBottomNav() {

        binding.bottomNav.run {
            //取消着色
            itemIconTintList = null
            //初始化
            selectedItemId = R.id.navi_home
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

        val navController = (childFragmentManager.findFragmentById(R.id.module_fragment_container) as NavHostFragment).navController
        binding.bottomNav.setupWithNavController(navController)

    }


}