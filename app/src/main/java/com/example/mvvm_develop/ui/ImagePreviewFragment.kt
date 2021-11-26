package com.example.mvvm_develop.ui

import android.annotation.SuppressLint
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.viewbinding.viewBinding
import com.example.mvvm_develop.R
import com.example.mvvm_develop.adapter.ImagePreviewAdapter
import com.example.mvvm_develop.databinding.FragmentImagePreviewBinding
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantParams

@Route(path = ConstantARouter.PreviewImageFragment)
class ImagePreviewFragment(private val imgList: ArrayList<String> = arrayListOf<String>()) : BaseFragment(R.layout.fragment_image_preview) {

    private val binding by viewBinding(FragmentImagePreviewBinding::bind)

    companion object {
        @JvmStatic
        fun newInstance(imgList: ArrayList<String>) = ImagePreviewFragment(imgList)
    }


    override suspend fun initData() {
        binding.previewTitle.text = "1/${imgList.size}"
        binding.previewVp.apply {
            adapter = ImagePreviewAdapter(imgList)
            offscreenPageLimit = 3
        }

        initParams()
    }

    /*初始化数据*/
    private fun initParams() {
        val list: List<String> ?= arguments?.getStringArray(ConstantParams.PreviewImageList)?.toList()
        val position: Int = arguments?.getInt(ConstantParams.PreviewImagePosition) ?: 1

        list?.let {
            imgList.clear()
            imgList.addAll(it)
            binding.previewVp.setCurrentItem(position,false)
            binding.previewVp.adapter?.notifyDataSetChanged()
        }
    }


}