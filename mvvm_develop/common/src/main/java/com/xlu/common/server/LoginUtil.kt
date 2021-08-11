package com.xlu.common.server

import com.alibaba.android.arouter.launcher.ARouter
import com.xlu.common.api.LoginItf

/**
 * @ClassName LoginUtil
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/9 16:13
 */
object LoginUtil {

    fun getLoginServer(): LoginItf {
        return ARouter.getInstance().navigation(LoginItf::class.java)//如果只有一个实现，这种方式也可以
        //return ARouter.getInstance().build(ConstantARouter.LoginItfImpl).navigation() as LoginInterface
    }
}