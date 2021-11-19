package com.xlu.module_tab1.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.utils.other.TimeUtil
import com.example.baselibrary.viewbinding.viewBinding
import com.xlu.module_tab1.R
import com.xlu.module_tab1.adapter.ImageListAdapter
import com.xlu.module_tab1.bean.Image
import com.xlu.module_tab1.databinding.FragmentCoilBinding


class FragmentCoil : BaseFragment(R.layout.fragment_coil) {

    private val binding by viewBinding(FragmentCoilBinding::bind)
    private val coilAdapter by lazy {
        ImageListAdapter()
    }


    override fun initData() {
        binding.coilList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = coilAdapter
        }

        val list = mutableListOf<Image>()
        for (i in 0..100){
            val id = (9000000..9580152).random()
            list.add(Image(id = i,url = "https://images.pexels.com/photos/$id/pexels-photo-$id.jpeg?cs=srgb&dl=pexels-denis-trushtin-$id.jpg&fm=jpg", title = TimeUtil.getCurrentTime()))
        }
        coilAdapter.addData(list)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentCoil()
    }
}