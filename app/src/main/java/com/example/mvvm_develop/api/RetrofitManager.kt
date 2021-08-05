package com.example.mvvm_develop.api

object RetrofitManager {

    /**
     * 用于存储ApiService
     */
    private val map = mutableMapOf<Class<*>, Any>()

    var baseUrl = "https://www.wanandroid.com/"

    private val retrofit by lazy {
        RetrofitFactory.factory(baseUrl)
    }

    //动态指定域名
    fun <T : Any> getApiService(apiClass: Class<T>): T {
        return getService(apiClass)
    }

    /**
     * 获取ApiService单例对象
     */
    private fun <T : Any> getService(apiClass: Class<T>): T{
        return if (map[apiClass] == null) {
            val t = retrofit.create(apiClass)
            if (map[apiClass] == null) {
                map[apiClass] = t
            }
            t
        } else {
            map[apiClass] as T
        }
    }
}