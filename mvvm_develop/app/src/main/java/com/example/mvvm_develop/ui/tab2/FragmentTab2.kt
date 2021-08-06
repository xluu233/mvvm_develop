package com.example.mvvm_develop.ui.tab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.base_databinding.DataBindingBaseFragment2
import com.example.baselibrary.common.clickNoRepeat
import com.example.baselibrary.common.setNoRepeatClick
import com.example.baselibrary.common.toast
import com.example.baselibrary.delegate.viewBinding
import com.example.mvvm_develop.CommonViewModel
import com.example.mvvm_develop.R
import com.example.mvvm_develop.databinding.FragmentTab2Binding
import com.xlu.module_jitpack.JitpackTest

class FragmentTab2 : BaseFragment(R.layout.fragment_tab2) {

    companion object {
        @JvmStatic
        fun newInstance() = FragmentTab2()
    }

    private val binding by viewBinding(FragmentTab2Binding::bind)
    private val viewmodel by activityViewModels<CommonViewModel>()

    override fun initData() {

        initClick()
    }

    private fun initClick() {
/*
        binding.button2.clickNoRepeat {
            navigate(R.id.action_mainFragment_to_dataBindingFragment)
        }
*/

        setNoRepeatClick(views = arrayOf(binding.button2,binding.button3),onClick = {
            when(it){
                binding.button2 -> {
                    navigate(R.id.action_mainFragment_to_dataBindingFragment)
                }
                binding.button3 -> {
                    toast(JitpackTest.getTime())
                }
            }
        })

    }

}