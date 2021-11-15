package com.xlu.common

import com.alibaba.android.arouter.launcher.ARouter
import com.xlu.common.constants.ConstantARouter


inline fun goLogin(){
    ARouter.getInstance().build(ConstantARouter.LoginActivity).navigation()
}