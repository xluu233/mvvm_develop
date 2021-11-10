package com.example.baselibrary.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.view.*
import java.lang.Exception

/**
 * Created by dee on 15/11/19.
 */
object ScreenUtils {

    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    /**
     * 判断是否是平板
     * @param context
     * @return
     */
    fun isPad(context: Context):Boolean{
        val i = 1920/1080
        val width = getScreenWidth(context)
        val height = getScreenHeight(context)
        val max = maxOf(width,height)
        val min = minOf(width,height)
        return max/min < i
    }

    fun setLayoutWidth(view: View, width: Int) {
        val lp = view.layoutParams
        lp.width = width
        view.layoutParams = lp
    }

    fun setLayoutWidthHeight(view: View, width: Int) {
        val lp = view.layoutParams
        lp.width = width
        lp.height = width
        view.layoutParams = lp
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    fun snapShotWithStatusBar(activity: Activity): Bitmap? {
        val view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val width = getScreenWidth(activity)
        val height = getScreenHeight(activity)
        var bp: Bitmap? = null
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height)
        view.destroyDrawingCache()
        return bp
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    fun snapShotWithoutStatusBar(activity: Activity): Bitmap? {
        val view = activity.window.decorView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val bmp = view.drawingCache
        val frame = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(frame)
        val statusBarHeight = frame.top
        val width = getScreenWidth(activity)
        val height = getScreenHeight(activity)
        var bp: Bitmap? = null
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight)
        view.destroyDrawingCache()
        return bp
    }

    /**
     * 判断是否虚拟键
     *
     * @return
     */
    fun getNavigationBarHeight(context: Context): Int {
        val hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey()
        val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
        return if (checkDeviceHasNavigationBar(context)) {
            val resources = context.resources
            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            //获取NavigationBar的高度
            resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }

    /**
     * 获取是否存在NavigationBar
     * @param context
     * @return
     */
    fun checkDeviceHasNavigationBar(context: Context): Boolean {
        var hasNavigationBar = false
        val rs = context.resources
        val id = rs.getIdentifier("config_showNavigationBar", "bool", "android")
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id)
        }
        try {
            val systemPropertiesClass = Class.forName("android.os.SystemProperties")
            val m = systemPropertiesClass.getMethod("get", String::class.java)
            val navBarOverride = m.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String
            if ("1" == navBarOverride) {
                hasNavigationBar = false
            } else if ("0" == navBarOverride) {
                hasNavigationBar = true
            }
        } catch (e: Exception) {
        }
        return hasNavigationBar
    }

    /**
     * 虚拟操作拦（home等）是否显示
     */
    fun isNavigationBarShow(activity: Activity): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val display = activity.windowManager.defaultDisplay
            val size = Point()
            val realSize = Point()
            display.getSize(size)
            display.getRealSize(realSize)
            realSize.y != size.y
        } else {
            val menu = ViewConfiguration.get(activity).hasPermanentMenuKey()
            val back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
            !(menu || back)
        }
    }
}