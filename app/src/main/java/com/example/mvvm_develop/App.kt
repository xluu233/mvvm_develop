package com.example.mvvm_develop

import android.app.Application
import com.example.baselibrary.log.xLog

/**
 * @ClassName App
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/26 17:09
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        xLog.init("MVVM",true)
    }
}