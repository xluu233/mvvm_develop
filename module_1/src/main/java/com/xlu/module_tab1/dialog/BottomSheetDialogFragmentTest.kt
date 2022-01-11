package com.xlu.module_tab1.dialog

import android.view.ViewGroup
import com.example.baselibrary.base.BaseBottomSheetDialogFragment
import com.example.baselibrary.utils.view.dp
import com.xlu.module_tab1.databinding.DialogBottomSheetBinding

/**
 * @ClassName BottomSheetDialogFragmentTest
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2022/1/11 10:04
 */
class BottomSheetDialogFragmentTest : BaseBottomSheetDialogFragment<DialogBottomSheetBinding>(DialogBottomSheetBinding::inflate) {

    companion object{
        @JvmStatic
        fun getInstance() = BottomSheetDialogFragmentTest()
    }

    init {
        cancel = true
        showShadow = false
        width = ViewGroup.LayoutParams.MATCH_PARENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        //默认的折叠高度
        peekHeight = 250.dp.toInt()
    }

    override suspend fun initData() {
    }

    override suspend fun initView() {
        binding.dialogTitle.text = "这个是BottomSheetDialog"
    }
}