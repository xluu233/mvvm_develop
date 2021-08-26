package com.xlu.module_tab1

import androidx.lifecycle.viewModelScope
import com.example.baselibrary.base.BaseViewModel
import com.example.baselibrary.http.ResultLiveData
import com.xlu.module_tab1.bean.Article

/**
 * @ClassName Tab1ViewModel
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/16 11:11
 */
class HomeViewModel : BaseViewModel() {

    private val repo by lazy { HomeRepo(viewModelScope) }

    val articleLiveData = ResultLiveData<Article>()
    fun getHomeArticle(page:Int){
        repo.getHomeArticleWithRoom(page,articleLiveData)
    }

    fun test(){
        repo.test()
    }
}