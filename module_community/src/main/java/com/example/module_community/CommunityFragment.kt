package com.example.module_community

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.bus.LiveDataBus
import com.example.baselibrary.utils.design.initFragment
import com.example.baselibrary.viewbinding.viewBinding
import com.example.module_community.databinding.FragmentCommunityBinding
import com.xlu.common.constants.ConstantEvent
import github.com.st235.lib_expandablebottombar.MenuItem
import github.com.st235.lib_expandablebottombar.OnItemClickListener


class CommunityFragment : BaseFragment(R.layout.fragment_community) {

    private val binding by viewBinding(FragmentCommunityBinding::bind)

    override fun initData() {
        initObserver()

        val viewPager = binding.communityViewpager
        val bottomBar = binding.communityBottomBar

        val fragmentList = listOf<Fragment>(
            ShareElementsFragment.newInstance(),
            FragmentCoil.newInstance(),
            ShareElementsFragment.newInstance(),
        )
        viewPager.apply {
            initFragment(this@CommunityFragment, fragmentList)
            offscreenPageLimit = 3
            currentItem = 0
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    Log.d(TAG, "onPageSelected: $position")
                    when(position){
                        0 -> {
                            bottomBar.menu.select(R.id.community_home)
                        }
                        1 -> {
                            bottomBar.menu.select(R.id.community_collection)
                        }
                        2 -> {
                            bottomBar.menu.select(R.id.community_settings)
                        }
                    }
                }
            })
        }

        /* 此控件会造成内存泄漏，不建议正式项目使用 */
        bottomBar.onItemSelectedListener = object : OnItemClickListener{
            override fun invoke(v: View, menuItem: MenuItem, byUser: Boolean) {
                when(menuItem.id){
                    R.id.community_home -> {
                        viewPager.currentItem = 0
                    }
                    R.id.community_collection -> {
                        viewPager.currentItem = 1
                    }
                    R.id.community_settings -> {
                        viewPager.currentItem = 2
                    }
                }
            }
        }
    }

    private fun initObserver() {
        LiveDataBus.with<Boolean>(ConstantEvent.HIDE_COMUNITY_BOTTOM_BAR).observe(this, Observer {
            val hide = it
            binding.communityBottomBar.let {
                it.animate().translationY(if (hide) it.height.toFloat() else 0f).start()
            }
        })
    }


}