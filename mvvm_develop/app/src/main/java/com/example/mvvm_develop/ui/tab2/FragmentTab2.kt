package com.example.mvvm_develop.ui.tab2

import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.common.setNoRepeatClick
import com.example.baselibrary.common.toast
import com.example.baselibrary.delegate.viewBinding
import com.example.baselibrary.log.xLog
import com.example.mvvm_develop.CommonViewModel
import com.example.mvvm_develop.R
import com.example.mvvm_develop.bean.RouterBeanSerializable
import com.xlu.common.constants.ConstantARouter
import com.example.mvvm_develop.databinding.FragmentTab2Binding
import com.xlu.common.login.LoginInterface
import com.xlu.common.constants.ConstantParams
import com.xlu.common.login.LoginUtil.getLoginServer
import com.xlu.module_jitpack.JitpackTest
import kotlin.math.log

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

        setNoRepeatClick(views = arrayOf(binding.button2,binding.button3,binding.button4,binding.button5,binding.button6),onClick = {
            when(it){
                binding.button2 -> {
                    navigate(R.id.action_mainFragment_to_dataBindingFragment)
                }
                binding.button3 -> {
                    toast(JitpackTest.getTime())
                }
                binding.button4 -> {
                    //跳转到Login模块 -> LoginActivity
                    ARouter.getInstance().build(ConstantARouter.LoginActivity).navigation()
                }
                binding.button5 -> {
                    //携带参数跳转
                    ARouter.getInstance().build(ConstantARouter.LoginActivity)
                        .withLong(ConstantParams.key1, 666L)
                        .withString(ConstantParams.key2, "888")
                        .withSerializable(ConstantParams.key3,RouterBeanSerializable(id = 123,name = "哈哈哈哈"))
                        .navigation()
                }
                binding.button6 -> {
                    val token:String = getLoginServer().getToken()
                    toast(token)
                    //xLog.d("token:${token}")
                }
            }
        })

    }

}