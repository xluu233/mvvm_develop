package com.xlu.module_tab1

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.baselibrary.vm.BaseViewModel
import com.example.baselibrary.http.ResultLiveData
import com.example.baselibrary.utils.log.xLog
import com.xlu.module_tab1.bean.Article

/**
 * @ClassName Tab1ViewModel
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/16 11:11
 */
class HomeViewModel : BaseViewModel() {

    private val repo by lazy { HomeRepo.getInstance(viewModelScope) }

    val articleLiveData = ResultLiveData<Article>()

    fun getHomeArticle(page:Int){
        repo.getHomeArticle(page,articleLiveData)
    }

    fun getHomeArticleExcute(page: Int){
        articleLiveData.postValue(repo.getHomeArticleExecute(page))
    }

    fun getHomeArticleWithJson(){
        repo.getHomeArticleWithJson()
    }


    val _text =  MutableLiveData<Int>(0)
    val xx = ObservableField<String>()

    fun add(){
        xx.set(System.currentTimeMillis().toString())
        _text.value = (_text.value ?:0) + 10
        xLog.d(_text.value.toString())
    }



}