package com.example.baselibrary.utils.other

import java.text.SimpleDateFormat
import java.util.*

/**
 * @ClassName TimeUtil
 * @Description 时间工具类
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/10/20 15:20
 */
object TimeUtil {

    fun getCurrentTime(): String {
        val timeStamp = System.currentTimeMillis() //获取当前时间戳
        //SimpleDateFormat("yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒")
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(Date(timeStamp.toString().toLong())) // 时间戳转换成时间
    }

}