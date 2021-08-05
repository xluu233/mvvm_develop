package com.example.mvvm_develop.api

import com.example.mvvm_develop.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.logging.Level


object RetrofitFactory {

    private val okHttpClientBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
            .readTimeout(
                10000,
                TimeUnit.MILLISECONDS
            )
            .connectTimeout(
                10000,
                TimeUnit.MILLISECONDS
            )
            .addInterceptor(getLogInterceptor())
    }


    fun factory(baseUrl:String): Retrofit {
        val okHttpClient = okHttpClientBuilder.build()
        val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
        return retrofit
    }


    /**
     * 获取日志拦截器
     */
    private fun getLogInterceptor(): Interceptor {
        val logger = HttpLoggingInterceptor().also {
            it.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        return logger
    }


}