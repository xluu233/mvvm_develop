package com.xlu.module_tab1.ui

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.bus.LiveDataBus
import com.example.baselibrary.common.setNoRepeatClick
import com.example.baselibrary.delegate.viewBinding
import com.example.baselibrary.log.xLog
import com.xlu.common.constants.ConstantEvent
import com.xlu.common.constants.ConstantParams
import com.xlu.module_tab1.HomeViewModel
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentTestBinding

class FragmentHomeMain : BaseFragment(R.layout.fragment_test) {

    private val binding by viewBinding(FragmentTestBinding::bind)
    private val viewModel: HomeViewModel by activityViewModels()



    override fun initData() {

        initClick()

        LiveDataBus.with<String>("test").observe(this, Observer {
            xLog.d(it)
        })

    }


    private fun initClick() {
        setNoRepeatClick(views = arrayOf(binding.button,binding.button2,binding.button3,binding.button4),onClick = {
            when(it){
                binding.button -> {
                    navigate(R.id.action_testFragment_to_fragment_mmkv)
                }
                binding.button2 -> {

                }
                binding.button3 -> {
                    LiveDataBus.with<String>("test").postData("ahahahhah")
                }
                binding.button4 -> {
                    LiveDataBus.with<Boolean>(ConstantEvent.app_go_databinding_fragment).postData(true)
                }
            }
        })
    }


}