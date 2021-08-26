package com.xlu.module_tab1

import android.util.Log
import com.airbnb.lottie.LottieCompositionFactory.fromJson
import com.example.baselibrary.base.BaseRepository
import com.example.baselibrary.db.BaseDatabase
import com.example.baselibrary.db.entity.NetCache
import com.example.baselibrary.http.*
import com.example.baselibrary.utils.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xlu.module_tab1.api.HomeApi
import com.xlu.module_tab1.bean.Article
import com.xlu.module_tab1.db.HomeDatabase
import kotlinx.coroutines.CoroutineScope
import java.lang.Exception
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @ClassName CommonRepo
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/12 10:18
 */
class HomeRepo(scope: CoroutineScope) : BaseRepository(scope) {

    private val TAG = "HomeRepo"

    private val mService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        RetrofitManager.getApiService(HomeApi::class.java)
    }

    private val homeDao by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        HomeDatabase.getInstance().homeDao()
    }



    private val cacheDao by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        BaseDatabase.getInstance().netCacheDao()
    }


    /**
     * 异步请求
     */
    fun getHomeArticle(page:Int,resultLiveData: ResultLiveData<Article>){
        launch(
            block = {
                mService.getHomeArticle(page)
            },
            response = {
                resultLiveData.postValue(it)
            }
        )
    }


    /**
     * 同步请求
     */
    fun getHomeArticleExecute(page:Int): ApiResponse<Article> {
        return execute {
            mService.getHomeArticle(page)
        }
    }


    /**
     * ROOM数据库与网络请求结合使用
     */
    fun getHomeArticleWithRoom(page:Int,resultLiveData: ResultLiveData<Article>){
        launch(
            block = {
                mService.getHomeArticle(page)
            },
            response = {
                if (it.state == NetState.STATE_SUCCESS){
                    //数据存入数据库
                    it.data?.let { article ->
                        homeDao.apply {
                            //删除上一次数据
                            deleteAll()
                            //插入新的数据
                            insert(article)
                        }
                    }
                }
                if (it.state == NetState.STATE_ERROR){
                    //从数据库读取上一次数据
                    it.data = homeDao.getAllData()[0]
                }
                
                resultLiveData.postValue(it)
            }
        )
    }

    /**
     * 转json缓存
     *
     */
    fun test(){
        launch(
            block = {
                mService.getHomeArticle(1)
            },
            response = {
                val md = "test"

                when(it.state){
                    NetState.STATE_SUCCESS -> {
                        //val json = JsonUtil.encode(it.data())
                        val json = Gson().toJson(it.data())
                        val netCache = NetCache(md,json)
                        cacheDao.insert(netCache)
                    }
                    NetState.STATE_FAILED,NetState.STATE_ERROR -> {
                        val netCache = cacheDao.query(md)

                        val type: Type = object : TypeToken<Article>() {}.type
                        it.data = Gson().fromJson(netCache.response, type)
                    }
                }

                it.data?.datas?.forEach {
                    Log.d(TAG, "test: ${it}")
                }
            }
        )
    }
}