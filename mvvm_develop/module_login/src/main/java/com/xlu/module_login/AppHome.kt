package com.xlu.module_login

import android.content.Context
import com.example.baselibrary.log.xLog
import com.hm.iou.lifecycle.annotation.AppLifecycle
import com.hm.lifecycle.api.IApplicationLifecycleCallbacks
import com.hm.lifecycle.api.IApplicationLifecycleCallbacks.NORM_PRIORITY

/**
 * @ClassName AppHome
 * @Description Application生命周期注入
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/10 15:17
 */
@AppLifecycle
class AppHome : IApplicationLifecycleCallbacks {

    /**
     * 用于设置优先级，即多个组件onCreate方法调用的优先顺序
     */
    override fun getPriority(): Int {
        return NORM_PRIORITY
    }

    override fun onCreate(context: Context?) {
        xLog.d("AppHome init")
    }

    override fun onTerminate() {
    }

    override fun onLowMemory() {
    }

    override fun onTrimMemory(level: Int) {
    }
}