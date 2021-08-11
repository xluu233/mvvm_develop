package com.example.baselibrary.http

import retrofit2.Retrofit

object RetrofitManager {

    /**
     * 用于存储ApiService
     */
    private val mapClass = mutableMapOf<Class<*>, Any>()

    private val mapRetrofit = mutableMapOf<String, Retrofit>()

    private var baseUrl = "https://www.wanandroid.com/"

    private var retrofit :Retrofit ?= null


    @Synchronized
    fun changeBase(url:String) : RetrofitManager = apply {
        baseUrl = url
        if (mapRetrofit[url] == null){
            retrofit = RetrofitFactory.factory(url)
            retrofit?.let {
                mapRetrofit[url] = it
            }
        }else{
            retrofit = mapRetrofit[url]
        }
    }

    @Synchronized
    fun <T : Any> getApiService(apiClass: Class<T>): T {
        if (retrofit == null){
            retrofit = RetrofitFactory.factory(baseUrl)
        }
        return getService(apiClass)
    }

    private fun <T : Any> getService(apiClass: Class<T>): T{
        return if (mapClass[apiClass] == null) {
            val t = retrofit!!.create(apiClass)
            if (mapClass[apiClass] == null) {
                mapClass[apiClass] = t
            }
            t
        } else {
            mapClass[apiClass] as T
        }
    }

}