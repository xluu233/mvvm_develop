package com.xlu.module_collection

import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.viewbinding.viewBinding
import com.example.baselibrary.utils.view.click
import com.example.xlulibrary.ToastBox
import com.xlu.common.bean.RouterBeanSerializable
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantParams
import com.xlu.common.server.ServerUtil.getLoginServer
import com.xlu.module_collection.databinding.FragmentCollectionBinding

class FragmentCollection : BaseFragment(R.layout.fragment_collection) {

    companion object {
        @JvmStatic
        fun newInstance() = FragmentCollection()
    }

    private val binding by viewBinding(FragmentCollectionBinding::bind)
    private val viewModel:CollectionViewModel by activityViewModels()

    override suspend fun initData() {
        initClick()
    }


    private fun initClick() {
        binding.testArouter.click {
            //跳转到Login模块 -> LoginActivity
            ARouter.getInstance().build(ConstantARouter.LoginActivity).navigation()
        }

        binding.testArouterParams.click {
            //携带参数跳转
            ARouter.getInstance().build(ConstantARouter.LoginActivity)
                .withLong(ConstantParams.key1, 666L)
                .withString(ConstantParams.key2, "888")
                .withSerializable(ConstantParams.key3,
                    RouterBeanSerializable(id = 123,name = "哈哈哈哈")
                )
                .navigation()
        }

        binding.testProvider.click {
            val token:String = getLoginServer().getToken()
            ToastBox.showToast(token)
        }

        binding.testFlow.click {

        }

    }

}