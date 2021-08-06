package com.example.mvvm_develop.api

import com.example.baselibrary.http.ApiResponse
import com.example.mvvm_develop.bean.BannerData
import retrofit2.http.GET

/**
 * @ClassName Api
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/12 10:17
 */
interface Api {

    @GET("banner/json")
    suspend fun loadProjectTree(): ApiResponse<List<BannerData>>

}