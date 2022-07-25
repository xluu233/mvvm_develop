package com.xlu.common.server

import com.example.baselibrary.bus.LiveDataBus
import com.xlu.common.constants.ConstantEvent


/**
 * TODO 导航接口
 */

/**
 * 与app模块交互
 */
fun navigateApp(event:String){
    LiveDataBus.with<String>(ConstantEvent.NAVIGATION_FRAGMENT_EVENT).postData(event)
}

fun navigateUI(event:String){
}