package com.xlu.module_tab1.ui

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.utils.other.TimeUtil
import com.example.baselibrary.viewbinding.viewBinding
import com.xlu.module_tab1.HomeViewModel
import com.xlu.module_tab1.R
import com.xlu.module_tab1.adapter.ImageListAdapter
import com.xlu.module_tab1.bean.Image
import com.xlu.module_tab1.databinding.FragmentCoilBinding


class FragmentCoil : BaseFragment(R.layout.fragment_coil) {

    private val viewModel by activityViewModels<HomeViewModel>()
    private val binding by viewBinding(FragmentCoilBinding::bind)

    private val coilAdapter by lazy {
        ImageListAdapter()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentCoil()
    }

    override fun initData() {
        binding.coilList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = coilAdapter
        }
        coilAdapter.addData(viewModel.getImageData())
    }


}