package com.example.module_community

import android.annotation.SuppressLint
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.viewbinding.viewBinding
import com.example.module_community.databinding.FragmentImagePreviewBinding

class ImagePreviewFragment(private val imgList: List<String>) : BaseFragment(R.layout.fragment_image_preview) {

    private val binding by viewBinding(FragmentImagePreviewBinding::bind)

    companion object {
        @JvmStatic
        fun newInstance(imgList: List<String>) = ImagePreviewFragment(imgList)
    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        binding.previewTitle.text = "1/${imgList.size}"
        binding.previewVp.apply {

        }
    }


}