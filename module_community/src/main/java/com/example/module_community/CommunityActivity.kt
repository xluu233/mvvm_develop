package com.example.module_community

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselibrary.base.BaseActivity
import com.example.baselibrary.bus.LiveDataBus
import com.example.baselibrary.utils.design.initFragment
import com.example.baselibrary.viewbinding.viewBinding
import com.example.module_community.databinding.FragmentCommunityBinding
import com.example.module_community.vm.CommunityViewModel
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantEvent
import github.com.st235.lib_expandablebottombar.MenuItem
import github.com.st235.lib_expandablebottombar.OnItemClickListener


@Route(path = ConstantARouter.CommunityActivity)
class CommunityActivity : BaseActivity(R.layout.fragment_community) {

    private val binding by viewBinding(FragmentCommunityBinding::bind)
    private val viewModel:CommunityViewModel by viewModels()

    override suspend fun initData(savedInstanceState: Bundle?) {
        initObserver()

        val viewPager = binding.communityViewpager
        val bottomBar = binding.communityBottomBar

        val fragmentList = listOf<Fragment>(
            ShareElementsFragment.newInstance(),
            FragmentCoil.newInstance(),
            ShareElementsFragment.newInstance(),
        )
        viewPager.apply {
            initFragment(this@CommunityActivity, fragmentList)
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
                            viewModel.hideCommunityBottomBar(false)
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

    override fun onActivityReenter(resultCode: Int, data: Intent) {
        //将共享元素tag发送给子fragment
        LiveDataBus.with<Int>(ConstantEvent.PHOTO_PREVIEW_SHARED_POSITION).postData(data.getIntExtra(COIL_POSITION,-1))
        super.onActivityReenter(resultCode, data)
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