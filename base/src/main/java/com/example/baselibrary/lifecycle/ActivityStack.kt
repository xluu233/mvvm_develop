package com.example.baselibrary.lifecycle

import android.app.Activity
import android.util.Log
import com.example.baselibrary.utils.log.xLog
import java.util.*
import kotlin.reflect.KClass

/**
 * Activity 栈管理类
 */
object ActivityStack {

    private val TAG = "ActivityStackManager"

    // 管理栈
    private val activityStack by lazy { Stack<Activity>() }

    //当前的 Activity
    val currentActivity: Activity? get() = if (!activityStack.empty()) activityStack.lastElement() else null

    //测试用
    fun print(){
        xLog.d(TAG, "print: ${activityStack.firstElement()},${activityStack.first()},${activityStack.lastElement()},${activityStack.last()}")
    }

    /**
     * 添加 Activity 到管理栈
     */
    fun addActivityToStack(activity: Activity) {
        activityStack.push(activity)
    }

    /**
     * 弹出栈内指定Activity 不finish
     */
    fun popActivityToStack(activity: Activity) {
        if (!activityStack.empty()) {
            activityStack.forEach {
                if (it == activity) {
                    activityStack.remove(activity)
                    return
                }
            }
        }
    }

    /**
     * 返回到上一个 Activity 并结束当前 Activity
     */
    fun backToPreActivity() {
        if (!activityStack.empty()) {
            val activity = activityStack.pop()
            if (!activity.isFinishing) activity.finish()
        }
    }

    /**
     * 根据类名判断是否是当前的 Activity
     */
    fun isCurrentActivity(cls: Class<*>): Boolean {
        return currentActivity?.javaClass == cls
    }

    /**
     * 判断是否存在该activity
     */
    fun <T : Activity> isInsideActivity(clazz: KClass<T>):Boolean = activityStack.any {
        it.javaClass == clazz
    }

    /**
     * 结束一个栈内指定类名的 Activity
     */
    fun finishActivity(cls: Class<*>) {
        activityStack.forEach {
            if (it.javaClass == cls) {
                if (!it.isFinishing) it.finish()
                return
            }
        }
    }

    /**
     * 弹出除了当前activity之外的其他Activity
     */
    fun finishOtherActivity() {
        val activityList = activityStack.toList()
        currentActivity?.run {
            activityList.forEach { activity ->
                if (this != activity) {
                    activityStack.remove(activity)
                    activity.finish()
                }
            }
        }
    }

    /**
     * 销毁当前activity
     */
    fun finishCurrent(){
        currentActivity?.finish()
    }

    /**
     * 返回到指定Activity
     */
    fun backToActivity(activityClass: Class<*>) {
        val activityList = activityStack.toList()
        // 获取栈最上面的Activity
        val lastElement = activityStack.lastElement()
        activityList.forEach {
            // 如果栈内存在该Activity就进行下一步操作
            if (it.javaClass == activityClass) {
                // 判断最上面的Activity是不是指定的Activity 不是就finish
                if (lastElement.javaClass == activityClass) {
                    return
                } else {
                    activityStack.remove(lastElement)
                    lastElement.finish()
                }
            }
        }
    }

    fun finishAllActivity(){
        activityStack.forEach {
            it.finish()
        }
    }
}