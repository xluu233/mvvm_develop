package com.example.mvvm_develop

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import com.example.baselibrary.base.BaseActivity
import com.example.baselibrary.common.initFragment
import com.example.baselibrary.delegate.viewBinding
import com.example.mvvm_develop.databinding.ActivityMainBinding
import com.example.mvvm_develop.ui.main.PlaceholderFragment
import com.example.mvvm_develop.ui.main.SectionsPagerAdapter

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    //private val binding by viewBinding(ActivityMainBinding::bind)


    override fun initData(savedInstanceState: Bundle?) {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
/*
        mBinding.viewPager.initFragment(
            this,
            listOf<Fragment>(PlaceholderFragment.newInstance(0),PlaceholderFragment.newInstance(1))
        )*/

        mBinding.fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }
    }



}