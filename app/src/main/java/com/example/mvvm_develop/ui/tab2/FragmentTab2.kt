package com.example.mvvm_develop.ui.tab2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.delegate.viewBinding
import com.example.mvvm_develop.CommonViewModel
import com.example.mvvm_develop.R
import com.example.mvvm_develop.databinding.FragmentTab2Binding

class FragmentTab2 : BaseFragment(R.layout.fragment_tab2) {

    companion object {
        @JvmStatic
        fun newInstance() = FragmentTab2()
    }

    private val binding by viewBinding(FragmentTab2Binding::bind)
    private val viewmodel by activityViewModels<CommonViewModel>()

    override fun initData() {

    }

}