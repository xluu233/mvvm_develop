package com.xlu.module_tab1

import android.util.Log
import com.example.baselibrary.vm.BaseRepository
import com.example.baselibrary.http.*
import com.xlu.module_tab1.api.HomeApi
import com.xlu.module_tab1.bean.Article
import com.xlu.module_tab1.db.HomeDatabase
import kotlinx.coroutines.CoroutineScope

/**
 * @ClassName CommonRepo
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/12 10:18
 */
class HomeRepo(scope: CoroutineScope) : BaseRepository(scope) {

    private val TAG = "HomeRepo"

    companion object{
        @Volatile
        private var instance:HomeRepo ?= null

        fun getInstance(scope: CoroutineScope) = instance ?: synchronized(this) {
            instance ?: HomeRepo(scope).also { instance = it }
        }
    }

    private val mService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        RetrofitManager.getApiService(HomeApi::class.java)
    }

    private val homeDao by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        HomeDatabase.getInstance().homeDao()
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
     * ROOM数据库与网络请求结合使用，不建议使用
     */
    fun getHomeArticleWithRoom(page:Int,resultLiveData: ResultLiveData<Article>){
        launch(
            block = {
                mService.getHomeArticle(page)
            },
            response = {
                if (it.netState == NetState.STATE_SUCCESS){
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
                if (it.netState == NetState.STATE_ERROR){
                    //从数据库读取上一次数据
                    it.data = homeDao.getAllData()[0]
                }
                
                resultLiveData.postValue(it)
            }
        )
    }

    /**
     * room保存json缓存，仅用于特定接口
     */
    fun getHomeArticleWithJson(){
        launch(
            block = {
                mService.getHomeArticle(1)
            },
            response = {
                //缓存json文件
                cacheJson(it,"test")

                it.data?.datas?.forEach {
                    Log.d(TAG, "test: ${it}")
                }
            }
        )
    }

}