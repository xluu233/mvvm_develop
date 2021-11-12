package com.example.mvvm_develop

import com.example.xlulibrary.ToastBox.Companion.toast
import com.xlu.common.api.AppItf

/**
 * @ClassName AppImpl
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/16 17:14
 */
//@Route(path = ConstantARouter.AppItfImpl)
class AppItfImpl : AppItf {
    override fun navigation(destination: String) {
        toast("hahahha")
    }
}