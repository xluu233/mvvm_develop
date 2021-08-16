package com.xlu.module_collection

import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.common.setNoRepeatClick
import com.example.baselibrary.common.toast
import com.example.baselibrary.delegate.viewBinding
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.server.LoginUtil.getLoginServer
import com.xlu.module_collection.databinding.FragmentCollectionBinding

class FragmentCollection : BaseFragment(R.layout.fragment_collection) {

    companion object {
        @JvmStatic
        fun newInstance() = FragmentCollection()
    }

    private val binding by viewBinding(FragmentCollectionBinding::bind)
    private val viewModel:CollectionViewModel by activityViewModels()

    override fun initData() {
        initClick()
    }


    private fun initClick() {

        setNoRepeatClick(views = arrayOf(binding.button2,binding.button3,binding.button4,binding.button5,binding.button6),onClick = {
            when(it){
                binding.button2 -> {
                    //navigate(R.id.action_mainFragment_to_dataBindingFragment)
                }
                binding.button3 -> {
                    //toast(JitpackTest.getTime())
                }
                binding.button4 -> {
                    //跳转到Login模块 -> LoginActivity
                    ARouter.getInstance().build(ConstantARouter.LoginActivity).navigation()
                }
                binding.button5 -> {
                    //携带参数跳转
//                    ARouter.getInstance().build(ConstantARouter.LoginActivity)
//                        .withLong(ConstantParams.key1, 666L)
//                        .withString(ConstantParams.key2, "888")
//                        .withSerializable(ConstantParams.key3,RouterBeanSerializable(id = 123,name = "哈哈哈哈"))
//                        .navigation()
                }
                binding.button6 -> {
                    val token:String = getLoginServer().getToken()
                    toast(token)
                }
            }
        })

    }

}