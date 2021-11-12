package com.example.baselibrary.utils.other

import android.graphics.Color
import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.example.baselibrary.BaseApp
import com.example.baselibrary.utils.activity.application
import java.lang.Exception

/**
 * 色处理工具类
 */
object ColorUtils {

    /**
     * 解析颜色
     */
    fun parseColor(colorStr: String, defaultColor: Int): Int {
        var colorStr = colorStr
        return if (TextUtils.isEmpty(colorStr)) {
            defaultColor
        } else try {
            if (!colorStr.startsWith("#")) {
                colorStr = "#$colorStr"
            }
            Color.parseColor(colorStr)
        } catch (e: Exception) {
            defaultColor
        }
    }

    fun parseColor(colorStr: String): Int {
        var colorStr = colorStr
        return if (TextUtils.isEmpty(colorStr)) {
            0
        } else try {
            if (!colorStr.startsWith("#")) {
                colorStr = "#$colorStr"
            }
            Color.parseColor(colorStr)
        } catch (e: Exception) {
            0
        }
    }

    /**
     * 解析颜色
     */
    fun parseColor(color: Int): Int {
        return ContextCompat.getColor(application, color)
    }

    /**
     * 设置html字体色值
     */
    fun setTextColor(text: String, color: String): String {
        return "<font color=#$color>$text</font>"
    }

}