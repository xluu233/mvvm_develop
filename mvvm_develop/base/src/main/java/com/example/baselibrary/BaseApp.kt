package com.example.baselibrary

import android.app.Application
import android.content.Context



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