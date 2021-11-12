package com.xlu.module_tab1.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.bus.LiveDataBus
import com.example.baselibrary.delegate.viewBinding
import com.example.baselibrary.utils.log.xLog
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.utils.view.setNoRepeatClick
import com.xlu.common.constants.ConstantEvent
import com.xlu.module_tab1.HomeViewModel
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentHomeBinding

class FragmentHomeMain : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by activityViewModels()



    override fun initData() {

        initClick()

        LiveDataBus.with<String>("test").observe(this, Observer {
            xLog.d(it)
        })

    }


    private fun initClick() {
        setNoRepeatClick(views = arrayOf(binding.button,binding.button3,binding.button4),onClick = {
            when(it){
                binding.button -> {
                    navigate(R.id.action_testFragment_to_fragment_mmkv)
                }
                binding.button3 -> {
                    LiveDataBus.with<String>("test").postData("ahahahhah")
                }
                binding.button4 -> {
                    LiveDataBus.with<Boolean>(ConstantEvent.app_go_databinding_fragment).postData(true)
                }
            }
        })


        binding.button5.setOnClickListener {
            navigate(R.id.action_HomeMainFragment_to_roomFragment)
        }



        binding.button6.click {

        }

    }


}