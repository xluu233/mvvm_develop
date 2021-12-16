package com.xlu.test

import com.example.baselibrary.BaseApp
import com.example.xlulibrary.ToastBox

/**
 * @ClassName App
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/6 15:52
 */
class AppHome : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        initSDK()
    }

    private fun initSDK() {
        ToastBox.showToast("initSDK")
    }

}