package com.example.baselibrary

import android.app.Application
import android.content.Context

/**
 * @date 2020/5/9
 * @author xlu
 */
open class BaseApp :Application() {


    companion object{
        private lateinit var baseApplication: BaseApp

        fun getContext(): Context {
            return baseApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        baseApplication = this
    }


}