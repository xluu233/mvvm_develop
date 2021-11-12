package com.xlu.module_login

import android.app.Application
import android.content.Context
import com.example.baselibrary.lifecycle.ApplicationLifecycle
import com.example.baselibrary.lifecycle.InitDepend
import com.example.baselibrary.utils.log.xLog
import com.google.auto.service.AutoService


/**
 * @ClassName AppHome
 * @Description Application生命周期注入
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/10 15:17
 */
@AutoService(ApplicationLifecycle::class)
class AppLogin : ApplicationLifecycle {

    private val TAG = "AppLogin"

    override fun onAttachBaseContext(context: Context) {
    }

    override fun onCreate(application: Application) {
    }

    override fun onTerminate(application: Application) {
    }

    override fun initByFrontDesk(): InitDepend {
        val worker = mutableListOf<() -> Any>()
        val main = mutableListOf<() -> Any>()

        worker.add { initSdkMain() }
        main.add { initSdkWorker() }
        return InitDepend(main, worker)
    }

    private fun initSdkWorker() {
        xLog.d(TAG, "initSdkWorker: ")
    }

    private fun initSdkMain() {
        xLog.d(TAG, "initSdkMain: ")
    }

    override fun initByBackstage() {
    }


}