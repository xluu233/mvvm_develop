package com.xlu.module_tab1.ui

import androidx.fragment.app.activityViewModels
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.db.MMKVUtil
import com.example.baselibrary.db.MMKV_TYPE
import com.example.baselibrary.viewbinding.viewBinding
import com.xlu.module_tab1.HomeViewModel
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentMmkvBinding


class FragmentMMKV : BaseFragment(R.layout.fragment_mmkv) {

    private val binding by viewBinding(FragmentMmkvBinding::bind)
    private val viewModel: HomeViewModel by activityViewModels()

    val key = "home_test"

    override fun initData() {

        binding.write.setOnClickListener {
            MMKVUtil.get(MMKV_TYPE.APP).encode(key,"this is mmkv_app params")
        }
        binding.write2.setOnClickListener {
            MMKVUtil.get(MMKV_TYPE.USER).encode(key,"this is mmkv_user params")
        }


        binding.read.setOnClickListener {
            binding.result.text = MMKVUtil.get(MMKV_TYPE.APP).decodeString(key)
        }
        binding.read2.setOnClickListener {
            binding.result.text = MMKVUtil.get(MMKV_TYPE.USER).decodeString(key)
        }



        binding.delete.setOnClickListener {
            MMKVUtil.get(MMKV_TYPE.USER).clearAll()
            MMKVUtil.get(MMKV_TYPE.APP).clearAll()
        }


    }

}