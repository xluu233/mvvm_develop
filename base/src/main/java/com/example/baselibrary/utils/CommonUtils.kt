package com.example.baselibrary.utils

import android.content.Context
import android.content.pm.PackageManager
import android.media.ExifInterface
import androidx.core.app.ActivityCompat
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * 复制原图Exif信息到压缩后图片的Exif信息
 * @param sourcePath 原图路径
 * @param targetPath 目标图片路径
 */
private fun copyExif(sourcePath: String, targetPath: String) {
    try {
        val source = ExifInterface(sourcePath)
        val target = ExifInterface(targetPath)
        source.javaClass.declaredFields.forEach { member ->//获取ExifInterface类的属性并遍历
            member.isAccessible = true
            val tag = member.get(source)//获取原图EXIF实例种的成员变量
            if (member.name.startsWith("TAG_") && tag is String) {//判断变量是否以TAG开头，并且是String类型
                target.setAttribute(tag, source.getAttribute(tag))//设置压缩图的EXIF信息
                target.saveAttributes()//保存属性更改
            }
        }
    } catch (e: Exception) {
    }
}

/**
 * 日期格式字符串转换成时间戳
 * @param date 字符串日期
 * @param format 如：yyyy-MM-dd HH:mm:ss
 * @return
 */
fun getCurentTime(): String {
    val timeStamp = System.currentTimeMillis() //获取当前时间戳
    //SimpleDateFormat("yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒")
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val sd = sdf.format(Date(timeStamp.toString().toLong())) // 时间戳转换成时间
    return sd
}

fun Context.hasPermission(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}




