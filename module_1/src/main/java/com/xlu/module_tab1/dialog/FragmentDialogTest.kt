package com.xlu.module_tab1.dialog

import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.utils.view.dp
import com.example.baselibrary.viewbinding.viewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xlu.common.hideBottomNav
import com.xlu.module_tab1.R
import com.xlu.module_tab1.databinding.FragmentDialogTestBinding

class FragmentDialogTest : BaseFragment(R.layout.fragment_dialog_test) {


    private val binding by viewBinding(FragmentDialogTestBinding::bind)

    override suspend fun initData() {
        binding.normalDialog.click {
            //中心渐入渐出
            CenterDialog.getInstance().show(parentFragmentManager,"test")
        }

        binding.bottomDialog.click {
            //底部弹出
            BottomDialog.getInstance().show(parentFragmentManager,"bottom")
        }

        binding.bottomSheet.click {
            //底部折叠布局
            val behavior = BottomSheetBehavior.from(binding.bottomSheetLayout)
            if (behavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                //如果是展开状态，则关闭，反之亦然
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            } else {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        binding.bottomSheetDialog.click {
            //没什么卵用，不建议使用
            val bottomSheetDialog = BottomSheetDialog(requireContext(),R.style.DialogThemeShadow).apply {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.setPeekHeight(400.dp.toInt(),true)

            }
            bottomSheetDialog.setContentView(R.layout.dialog_bottom_sheet)
            bottomSheetDialog.show()
        }

        binding.bottomSheetDialog2.click {
            //基于DialogFragment的BottomSheetDialogFragment
            BottomSheetDialogFragmentTest.getInstance().show(parentFragmentManager,"bottomsheet")
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        hideBottomNav(false)
    }
}