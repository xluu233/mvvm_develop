package com.xlu.common

import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.bus.LiveDataBus
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantEvent


inline fun goLogin(){
    ARouter.getInstance().build(ConstantARouter.LoginActivity).navigation()
}


//隐藏主页底部导航栏
inline fun hideBottomNav(hide:Boolean){
    LiveDataBus.with<Boolean>(ConstantEvent.HIDE_APP_BOTTOM_NAVIGATION).postData(hide)
}