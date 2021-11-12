package com.example.baselibrary

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.startup.Initializer
import com.example.baselibrary.lifecycle.ActivityStack
import com.example.baselibrary.utils.log.xLog

/**
 * @ClassName AppInit
 * @Description StartUp初始化
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/11 17:22
 */
class AppInit : Initializer<Unit> {

    companion object{
        internal lateinit var application: Application
    }

    private val TAG = "AppInit"
    private val activityLifecycle = object : Application.ActivityLifecycleCallbacks{
        override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
            ActivityStack.addActivityToStack(activity)
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivityCreated")
        }

        override fun onActivityStarted(activity: Activity) {
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivityStarted")
        }

        override fun onActivityResumed(activity: Activity) {
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivityResumed")
        }

        override fun onActivityPaused(activity: Activity) {
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivityPaused")
        }

        override fun onActivityStopped(activity: Activity) {
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivityStopped")
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivitySaveInstanceState")
        }

        override fun onActivityDestroyed(activity: Activity) {
            ActivityStack.popActivityToStack(activity)
            xLog.e(TAG, "${activity.javaClass.simpleName} --> onActivityDestroyed")
        }
    }

    override fun create(context: Context) {
        application = context as Application
        application.registerActivityLifecycleCallbacks(activityLifecycle)
    }


    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}