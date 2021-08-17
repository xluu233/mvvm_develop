package com.xlu.module_tab1

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.bus.LiveDataBus
import com.example.baselibrary.common.setNoRepeatClick
import com.example.baselibrary.delegate.viewBinding
import com.example.baselibrary.log.xLog
import com.xlu.common.constants.ConstantARouter
import com.xlu.module_tab1.databinding.FragmentHomeBinding

@Route(path = ConstantARouter.FragmentTab1_Main)
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    companion object{
        @JvmStatic
        fun newInstance(): Fragment = HomeFragment()
    }

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by activityViewModels()


    override fun initData() {
        initClick()

        LiveDataBus.with<String>("test").observe(this, Observer {
            xLog.d(it)
        })
    }

    private fun initClick() {
        setNoRepeatClick(views = arrayOf(binding.button,binding.button2,binding.button3),onClick = {
            when(it){
                binding.button -> {
                    findNavController().navigate(R.id.action_homeFragment_to_fragment_mmkv)
                    //navigate(R.id.action_homeFragment_to_fragment_mmkv)
                }
                binding.button2 -> {

                }
                binding.button3 -> {
                    LiveDataBus.with<String>("test").postData("ahahahhah")
                }
            }
        })
    }

}