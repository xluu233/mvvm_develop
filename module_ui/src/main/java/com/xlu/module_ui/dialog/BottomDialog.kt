package com.xlu.module_ui.dialog

import android.view.Gravity
import android.view.ViewGroup
import com.example.baselibrary.base.BaseDialogFragment
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.utils.view.dp
import com.example.xlulibrary.ToastBox
import com.xlu.module_ui.R
import com.xlu.module_ui.databinding.DialogTestBinding

/**
 * @ClassName TestDialog
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/15 16:26
 */
class BottomDialog : BaseDialogFragment<DialogTestBinding>(DialogTestBinding::inflate) {

    companion object{
        @JvmStatic
        fun getInstance() = BottomDialog()
    }

    init {
        //进行初始化参数配置
        initParams(
            cancel = false,
            gravity = Gravity.BOTTOM,
            width = ViewGroup.LayoutParams.MATCH_PARENT,
            height = 300.dp.toInt(),
            layoutBackground = R.drawable.dialog_bottom_back,
            anim = R.style.dialogAnimation_bottom,
            style = R.style.DialogThemeShadow
        )
    }

    override suspend fun initView() {
        binding.confirmButton.click {
            dismiss()
        }
    }

    override suspend fun initData() {

    }

    override fun dismiss() {
        super.dismiss()
        ToastBox.showToast("dismiss")
    }

}