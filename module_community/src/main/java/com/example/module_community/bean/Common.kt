package com.example.module_community.bean

import androidx.annotation.DrawableRes

/**
 * @ClassName Common
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/25 13:23
 */
data class Community(
    //名称
    val name:String,
    val subName:String,

    val id:Int,

    //头像
    @DrawableRes
    val icon:Int,

    //内容
    val content:String = "",

    //九宫格图片
    val img:ArrayList<String>,

    //是否关注了
    val focus:Boolean = false
)
