package com.example.mvvm_develop

import com.alibaba.android.arouter.facade.annotation.Route
import com.example.baselibrary.common.toast
import com.xlu.common.api.AppItf
import com.xlu.common.constants.ConstantARouter

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