package com.xlu.module_tab1.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.viewbinding.viewBinding
import com.xlu.common.hideBottomNav
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentDialogTestBinding

class FragmentDialogTest : BaseFragment(R.layout.fragment_dialog_test) {


    private val binding by viewBinding(FragmentDialogTestBinding::bind)

    override suspend fun initData() {
        binding.normalDialog.click {
            CenterDialog.getInstance().show(parentFragmentManager,"test")
        }

        binding.bottomDialog.click {
            BottomDialog.getInstance().show(parentFragmentManager,"bottom")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        hideBottomNav(false)
    }
}