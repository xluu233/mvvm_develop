package com.xlu.module_login

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.api.LoginItf

/**
 * @ClassName LoginImpl
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/9 16:02
 */
@Route(path = ConstantARouter.LoginItfImpl)
class LoginImpl : LoginItf {

    override fun getToken(): String {
        return "this is login token"
    }

    override fun getUserName(): String {
        return "name"
    }

    override fun getUserID(): Int {
        return 123
    }

    override fun init(context: Context?) {

    }

}