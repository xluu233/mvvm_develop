package com.xlu.common.login

import com.alibaba.android.arouter.launcher.ARouter
import com.xlu.common.constants.ConstantARouter

/**
 * @ClassName LoginUtil
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/9 16:13
 */
object LoginUtil {

    fun getLoginServer():LoginInterface{
        return ARouter.getInstance().navigation(LoginInterface::class.java)//如果只有一个实现，这种方式也可以
        //return ARouter.getInstance().build(ConstantARouter.LoginItfImpl).navigation() as LoginInterface
    }
}