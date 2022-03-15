package com.xlu.module_ui.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.utils.design.addOnTabSelectedListener
import com.example.baselibrary.utils.design.setupWithViewPager2
import com.example.baselibrary.viewbinding.viewBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.xlu.common.hideBottomNav
import com.xlu.module_ui.R
import com.xlu.module_ui.adapter.CardAdapter
import com.xlu.module_ui.databinding.FragmentTablayoutBinding
import android.animation.ValueAnimator
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView


class FragmentTab : BaseFragment(R.layout.fragment_tablayout) {


    private val binding by viewBinding(FragmentTablayoutBinding::bind)

    @SuppressLint("ResourceAsColor")
    override suspend fun initData() {
        binding.pager.apply {
            val colors = arrayListOf<Int>().apply {
                add(R.color.deepskyblue)
                add(R.color.antiquewhite)
                add(R.color.lightsalmon)
                add(R.color.dimgrey)
            }
            adapter = CardAdapter(colors)

        }

        //原始调用
        TabLayoutMediator(binding.tab1, binding.pager,object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.text = position.toString()
            }
        }).attach()

        //扩展工具
        binding.tab2.apply {
            setupWithViewPager2(binding.pager){tab: TabLayout.Tab, position: Int ->
                tab.text = "😍$position"
            }
            setSelectedTabIndicator(null)
        }

        binding.tab3.apply {
            setupWithViewPager2(binding.pager){tab: TabLayout.Tab, position: Int ->
                tab.text = "😎$position"
            }
            tabGravity = TabLayout.GRAVITY_CENTER
        }

        binding.tab4.apply {
            setupWithViewPager2(binding.pager){tab: TabLayout.Tab, position: Int ->
                val tabView = TextView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
                    text = "🤩$position"
                    setTextColor(R.color.black)
                }
                tab.customView = tabView
            }
            addOnTabSelectedListener(
                onTabSelected = {
                    changeTabSelect(it)
                },
                onTabReselected = {
                    //changeTabSelect(it)
                },
                onTabUnselected = {
                    changeTabNormal(it)
                }
            )
        }

        //下标动画
        binding.tab5.apply {
            setupWithViewPager2(binding.pager){tab: TabLayout.Tab, position: Int ->
                tab.text = "😴  $position 🥱"
            }
        }

        binding.tab6.apply {
            setupWithViewPager2(binding.pager){tab: TabLayout.Tab, position: Int ->
                val tabView = TextView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
                    text = "👮‍♀️$position👮‍♂️"
                    setTextColor(R.color.black)
                }
                tab.customView = tabView
            }
            addOnTabSelectedListener(
                onTabSelected = {
                    changeTabSelect(it)
                },
                onTabReselected = {
                    //changeTabSelect(it)
                },
                onTabUnselected = {
                    changeTabNormal(it)
                }
            )
        }

    }

    private fun changeTabNormal(tab: TabLayout.Tab) {
        val view = tab.customView ?: return
        val anim = ValueAnimator.ofFloat(1.5f, 1.0f).setDuration(300)
        anim.addUpdateListener { animation ->
            val cVal = animation.animatedValue as Float
            view.alpha = 1f - (1f - cVal) * (0.5f / 0.1f)
            view.scaleX = cVal
            view.scaleY = cVal
            //view.alpha = cVal/1.5f
        }
        anim.start()
    }

    private fun changeTabSelect(tab: TabLayout.Tab) {
        val view = tab.customView ?: return
        val anim = ValueAnimator.ofFloat(1.0f, 1.5f).setDuration(300)
        anim.addUpdateListener { animation ->
            val cVal = animation.animatedValue as Float
            view.alpha = 0.5f + (cVal - 1f) * (0.5f / 0.1f)
            view.scaleX = cVal
            view.scaleY = cVal
            //view.alpha = cVal/1.5f
        }
        anim.start()
    }

    override fun onDestroyView() {
        hideBottomNav(false)
        super.onDestroyView()
    }

}