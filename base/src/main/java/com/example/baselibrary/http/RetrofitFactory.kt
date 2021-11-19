package com.example.baselibrary.http

import android.content.Context
import com.example.baselibrary.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import android.net.ConnectivityManager
import com.example.baselibrary.utils.other.FilePath
import com.example.baselibrary.utils.activity.application
import java.io.File


object RetrofitFactory {

    const val TAG = "RetrofitFactory"

    //添加Cache拦截器，有网时添加到缓存中，无网时取出缓存
    var file: File = File(FilePath.getAppCachePath(),"http_cache")
    var cache = Cache(file,(20 * 1024 * 1024).toLong())

    private val okHttpClientBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .cache(cache)
            .addInterceptor(getLogInterceptor())
            .addInterceptor(getCacheInterceptor())  //设置缓存
    }


    fun factory(baseUrl: String): Retrofit {
        val okHttpClient = okHttpClientBuilder.build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }


    /**
     * 获取日志拦截器
     */
    private fun getLogInterceptor(): Interceptor{
        return HttpLoggingInterceptor().also {
            it.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }


    /**
     * 设置缓存
     */
    private fun getCacheInterceptor():Interceptor = Interceptor { chain ->
        var request = chain.request()
        //当没有网络时
        if (!isNetworkConnected()) {
            request = request.newBuilder() //CacheControl.FORCE_CACHE; //仅仅使用缓存
                //CacheControl.FORCE_NETWORK;// 仅仅使用网络
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }

        val proceed = chain.proceed(request)
        if (isNetworkConnected()) {
            //有网络时
            proceed.newBuilder() //清除头信息
                .header("Cache-Control", "public, max-age=" + 60)
                .removeHeader("Progma")
                .build()
        } else {
            //没网络时
            val maxTime = 4 * 24 * 60 * 60 //离线缓存时间：4周
            proceed.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxTime")
                .removeHeader("Progma")
                .build()
        }
    }

    /**
     * 网络状态判断
     */
    private fun isNetworkConnected(): Boolean {
        val mConnectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mNetworkInfo = mConnectivityManager.activeNetworkInfo
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable
        }
        return false
    }

}