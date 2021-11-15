package com.xlu.module_tab1.api

import com.example.baselibrary.http.ApiResponse
import com.xlu.common.bean.BannerData
import com.xlu.module_tab1.bean.Article
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @ClassName HomeApi
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/24 15:00
 */
interface HomeApi {

    @GET("article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): ApiResponse<Article>



    @GET("banner/json")
    suspend fun loadProjectTree(): ApiResponse<List<BannerData>>

}