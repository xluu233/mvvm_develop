package com.xlu.module_tab1.mmkv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.db.MMKVUtil
import com.example.baselibrary.db.MMKV_TYPE
import com.example.baselibrary.delegate.viewBinding
import com.xlu.module_tab1.HomeViewModel
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentHomeBinding
import com.xlu.module_tab1.databinding.FragmentMmkvBinding


class Fragment_mmkv : BaseFragment(R.layout.fragment_mmkv) {

    private val binding by viewBinding(FragmentMmkvBinding::bind)
    private val viewModel: HomeViewModel by activityViewModels()

    val key = "home_test"

    override fun initData() {

        binding.write.setOnClickListener {
            MMKVUtil.encode(key,"app_test")
        }

        binding.read.setOnClickListener {
            binding.result.text = MMKVUtil.decodeString(key)
        }


        binding.change.setOnClickListener {
            //切换到用户相关配置
            MMKVUtil.changeMMKV(MMKV_TYPE.USER).encode(key,"user_test")
        }


        binding.delete.setOnClickListener {
            MMKVUtil.clearAll(MMKV_TYPE.USER)
        }


    }

}