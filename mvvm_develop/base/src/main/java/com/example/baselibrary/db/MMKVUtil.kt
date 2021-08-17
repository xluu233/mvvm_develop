package com.example.baselibrary.db

import android.app.Application
import android.os.Parcelable
import com.tencent.mmkv.MMKV
import java.util.*

/**
 * @ClassName mmkvUtils
 * @Description mmkv存储工具类，参考：https://github.com/Tencent/MMKV/wiki/android_tutorial_cn
 * @Author AlexLu_1406496344@qq.com
 * @Date 2020/12/14 16:06
 */


enum class MMKV_TYPE{
    USER,
    APP
}


class MMKVUtil {

    companion object{

        @JvmField
        val instance = MMKVUtil()

        //对于app和用户可以设置不同的mmkv
        @Volatile
        var mmkv: MMKV ?= null

        //用户相关
        private val userMMKV by lazy {
            MMKV.mmkvWithID("user",MMKV.MULTI_PROCESS_MODE)
        }

        //app配置相关
        private val appMMKV by lazy {
            MMKV.mmkvWithID("app",MMKV.MULTI_PROCESS_MODE)
        }

        //初始化
        fun init(app:Application){
            MMKV.initialize(app)
            mmkv = appMMKV
        }

        @Synchronized
        fun get(type: MMKV_TYPE):MMKVUtil = instance.apply{
            mmkv = when(type){
                MMKV_TYPE.USER -> {
                    userMMKV
                }
                MMKV_TYPE.APP -> {
                    appMMKV
                }
            }
        }
    }

    /*-------------Encode----------*/

    fun encode(key: String, value: Any?) {
        when (value) {
            is String -> mmkv?.encode(key, value)
            is Float -> mmkv?.encode(key, value)
            is Boolean -> mmkv?.encode(key, value)
            is Int -> mmkv?.encode(key, value)
            is Long -> mmkv?.encode(key, value)
            is Double -> mmkv?.encode(key, value)
            is ByteArray -> mmkv?.encode(key, value)
            is Nothing -> return
        }
    }

    fun <T : Parcelable> encode(key: String, t: T?) {
        if(t == null){
            return
        }
        mmkv?.encode(key, t)
    }

    fun encode(key: String, sets: Set<String>?) {
        if(sets ==null){
            return
        }
        mmkv?.encode(key, sets)
    }

    /*------------Decode-----------*/

    fun decodeInt(key: String): Int? {
        return mmkv?.decodeInt(key)
    }

    fun decodeString(key: String): String?{
        return mmkv?.decodeString(key)
    }

    fun decodeDouble(key: String): Double? {
        return mmkv?.decodeDouble(key)
    }

    fun decodeLong(key: String): Long? {
        return mmkv?.decodeLong(key)
    }

    fun decodeBoolean(key: String): Boolean? {
        return mmkv?.decodeBool(key)
    }

    fun decodeFloat(key: String): Float? {
        return mmkv?.decodeFloat(key)
    }

    fun decodeByteArray(key: String): ByteArray? {
        return mmkv?.decodeBytes(key)
    }

    fun <T : Parcelable> decodeParcelable(key: String, tClass: Class<T>): T? {
        return mmkv?.decodeParcelable(key, tClass)
    }

    fun decodeStringSet(key: String): Set<String>? {
        return mmkv?.decodeStringSet(key, Collections.emptySet())
    }

    /*------------Delete-----------*/

    //删除key
    fun removeKey(key: String) {
        mmkv?.removeValueForKey(key)
    }

    //删除所有
    fun clearAll() {
        mmkv?.clearAll()
        mmkv = null
    }

}

