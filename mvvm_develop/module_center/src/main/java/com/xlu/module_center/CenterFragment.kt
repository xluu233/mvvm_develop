package com.xlu.module_center

import androidx.fragment.app.activityViewModels
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.delegate.viewBinding
import com.xlu.module_center.databinding.FragmentCenterBinding

class CenterFragment : BaseFragment(R.layout.fragment_center) {

    companion object {
        @JvmStatic
        fun newInstance() = CenterFragment()
    }

    private val binding by viewBinding(FragmentCenterBinding::bind)
    private val viewModelodel : CenterViewModel by activityViewModels()

    override fun initData() {

    }

}