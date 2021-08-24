package com.xlu.module_tab1

import android.content.Context
import com.example.baselibrary.base.BaseRepository
import com.example.baselibrary.http.NetState
import com.example.baselibrary.http.ResultLiveData
import com.example.baselibrary.http.RetrofitManager
import com.xlu.module_tab1.api.HomeApi
import com.xlu.module_tab1.bean.Article
import com.xlu.module_tab1.db.Database
import kotlinx.coroutines.CoroutineScope

/**
 * @ClassName CommonRepo
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/12 10:18
 */
class HomeRepo(scope: CoroutineScope) : BaseRepository(scope) {

    private var mService = RetrofitManager.getApiService(HomeApi::class.java)


    /**
     * ROOM数据库与网络请求结合使用
     */
    fun getHomeArticle(page:Int,resultLiveData: ResultLiveData<Article>){
        launch(
            block = {
                mService.getHomeArticle(page)
            },
            response = {
                if (it.state == NetState.STATE_SUCCESS){
                    //数据存入数据库
                    it.data?.let { article ->
                        Database.getInstance().homeDao().insert(article)
                    }
                }
                if (it.state == NetState.STATE_ERROR){
                    //从数据库读取上一次数据
                    it.data = Database.getInstance().homeDao().getAllData()[0]
                }

                resultLiveData.postValue(it)
            }
        )
    }

}