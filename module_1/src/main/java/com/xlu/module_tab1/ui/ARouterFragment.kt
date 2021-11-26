package com.xlu.module_tab1.ui

import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.viewbinding.viewBinding
import com.xlu.common.constants.ConstantARouter
import com.xlu.module_tab1.HomeViewModel
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentArouterBinding

@Route(path = ConstantARouter.ARouterFragment)
class ARouterFragment : BaseFragment(R.layout.fragment_arouter) {

    private val binding by viewBinding(FragmentArouterBinding::bind)
    private val viewModel by activityViewModels<HomeViewModel>()


    @Autowired
    @JvmField var id:Long = 0L

    @Autowired
    @JvmField var name:String = ""


    override suspend fun initData() {
        ARouter.getInstance().inject(this)

        binding.textView4.text = "id:${id},name:${name}"
    }


}