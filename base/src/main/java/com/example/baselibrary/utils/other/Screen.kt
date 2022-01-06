/*
 * Copyright (c) 2021. Dylan Cai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("unused")

package com.example.baselibrary.utils.other

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.fragment.app.Fragment
import com.example.baselibrary.utils.activity.application
import java.lang.Exception


inline val screenWidth: Int get() = application.resources.displayMetrics.widthPixels

inline val screenHeight: Int get() = application.resources.displayMetrics.heightPixels


inline var Fragment.isLandscape: Boolean
  get() = activity?.isLandscape == true
  set(value) {
    activity?.isLandscape = value
  }

inline var Activity.isLandscape: Boolean
  get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
  set(value) {
    requestedOrientation = if (value) {
      ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    } else {
      ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
  }

inline var Fragment.isPortrait: Boolean
  get() = activity?.isPortrait == true
  set(value) {
    activity?.isPortrait = value
  }

inline var Activity.isPortrait: Boolean
  get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
  set(value) {
    requestedOrientation = if (value) {
      ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    } else {
      ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
  }



/**
 * 判断是否是平板
 * @param context
 * @return
 */
fun isPad():Boolean{
  val i = 1920/1080
  return screenHeight/ screenWidth < i
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
  val width = screenWidth
  val height = screenHeight
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
  val width = screenWidth
  val height = screenHeight
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