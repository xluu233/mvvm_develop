package com.example.mvvm_develop

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.BaseApp
import com.example.baselibrary.log.xLog
import com.example.baselibrary.BuildConfig.DEBUG

/**
 * @ClassName App
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/26 17:09
 */
class App : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        xLog.init("MVVM",true)

        initARouter()
    }

    private fun initARouter() {
        if (DEBUG) return
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this)
    }


}