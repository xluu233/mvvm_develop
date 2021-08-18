package com.example.mvvm_develop.test.arouter

import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.delegate.viewBinding
import com.example.mvvm_develop.CommonViewModel
import com.example.mvvm_develop.R
import com.xlu.common.constants.ConstantARouter
import com.example.mvvm_develop.databinding.FragmentArouterBinding

@Route(path = ConstantARouter.ARouterFragment)
class ARouterFragment : BaseFragment(R.layout.fragment_arouter) {

    private val binding by viewBinding(FragmentArouterBinding::bind)
    private val viewmodel by activityViewModels<CommonViewModel>()


    @Autowired
    @JvmField var id:Long = 0L

    @Autowired
    @JvmField var name:String = ""


    override fun initData() {
        ARouter.getInstance().inject(this)

        binding.textView4.text = "id:${id},name:${name}"
    }


}