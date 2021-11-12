package com.example.baselibrary.utils.other

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * @ClassName JsonUtil
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/26 14:20
 */
object JsonUtil {

    //val response = Gson().fromJson(json,ApiResponse<Article>().javaClass)
//    val type: Type = object : TypeToken<ApiResponse<Article>>() {}.type
//    val response: ApiResponse<Article> = Gson().fromJson(json, type)

    //不可用
    private fun <T> decode(json: String): T {
        val type: Type = object : TypeToken<T>() {}.type
        return Gson().fromJson(json, type)
    }

    fun encode(t: Any):String{
        return Gson().toJson(t)
    }

}