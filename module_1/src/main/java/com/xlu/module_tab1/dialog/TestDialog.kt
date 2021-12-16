package com.xlu.module_tab1.dialog

import com.example.baselibrary.base.BaseDialogFragment
import com.example.baselibrary.utils.view.click
import com.example.baselibrary.utils.view.dp
import com.example.xlulibrary.ToastBox
import com.xlu.module_tab1.databinding.DialogTestBinding

/**
 * @ClassName TestDialog
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/15 16:26
 */
class TestDialog : BaseDialogFragment<DialogTestBinding>(DialogTestBinding::inflate) {

    companion object{
        @JvmStatic
        fun getInstance() = TestDialog()
    }

    init {
        //进行初始化参数配置
        initParams(
            cancel = false,
            width = 300.dp.toInt(),
            height = 200.dp.toInt()
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