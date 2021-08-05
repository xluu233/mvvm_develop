package com.example.mvvm_develop

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.delegate.viewBinding
import com.example.mvvm_develop.databinding.FragmentMainBinding
import com.example.mvvm_develop.ui.tab1.FragmentTab1
import com.example.mvvm_develop.ui.tab2.FragmentTab2
import com.example.mvvm_develop.ui.tab3.FragmentTab3


class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    private val commonViewModel by activityViewModels<CommonViewModel>()


    private val fragmentTab1 by lazy {
        FragmentTab1.newInstance()
    }

    private val fragmentTab2 by lazy {
        FragmentTab2.newInstance()
    }

    private val fragmentTab3 by lazy {
        FragmentTab3.newInstance()
    }

    override fun initData() {

        initBottomNav()
    }


    /**
     * TODO 初始化底部导航栏
     * 如果有滑动需求可以采用viewpager实现
     */
    private fun initBottomNav() {
        binding.bottomNav.run {
            //取消着色
            itemIconTintList = null
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.fragmentTab1 -> {
                        switchFragment(fragmentTab1)
                    }
                    R.id.fragmentTab2 -> {
                        switchFragment(fragmentTab2)
                    }
                    R.id.fragmentTab3 -> {
                        switchFragment(fragmentTab3)
                    }
                }
                // 这里注意返回true,否则点击失效
                true
            }
            selectedItemId = R.id.fragmentTab1

            //去掉长按toast
            val ids = mutableListOf<Int>()
            ids.add(R.id.fragmentTab1)
            ids.add(R.id.fragmentTab2)
            ids.add(R.id.fragmentTab3)
            val bottomViewGroup : ViewGroup = this.getChildAt(0) as ViewGroup
            for (i in 0 until ids.size){
                bottomViewGroup.getChildAt(i).findViewById<View>(ids[i]).setOnLongClickListener {

                    true
                }
            }
        }
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