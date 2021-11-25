package com.example.module_community

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.viewbinding.viewBinding
import com.example.module_community.adapter.ImageListAdapter
import com.example.module_community.databinding.FragmentCoilBinding
import com.example.module_community.vm.CommunityViewModel
import com.xlu.common.hideBottomNav

class FragmentCoil : BaseFragment(R.layout.fragment_coil) {

    private val viewModel by activityViewModels<CommunityViewModel>()
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


    override fun onDestroyView() {
        super.onDestroyView()
        hideBottomNav(false)
    }
}