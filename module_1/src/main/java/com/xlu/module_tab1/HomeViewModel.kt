package com.xlu.module_tab1

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.baselibrary.vm.BaseViewModel
import com.example.baselibrary.http.ResultLiveData
import com.example.baselibrary.utils.activity.application
import com.example.baselibrary.utils.log.xLog
import com.example.baselibrary.utils.media.TextFileUtil
import com.example.baselibrary.utils.other.TimeUtil
import com.example.baselibrary.utils.other.getContext
import com.xlu.module_tab1.bean.Article
import com.xlu.module_tab1.bean.Image
import java.io.*

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


    fun getImageData():List<Image>{
        val list = mutableListOf<Image>()
        val urlList = readAssert("url.txt")

        for (i in 0 until 100){
            list.add(Image(id = i,url = urlList[i], title = TimeUtil.getCurrentTime()))
        }
        return list
    }

    private fun readAssert(name:String):List<String>{
        val list = mutableListOf<String>()

        try {
            val inputStream = application.assets.open(name)
            val inputreader = InputStreamReader(inputStream, "UTF-8")
            val buffreader = BufferedReader(inputreader)
            var line = ""
            //分行读取
            while (buffreader.readLine()?.also { line=it } != null) {
                list.add(line)
            }
            inputStream.close()
        } catch (e: IOException) {
            Log.d("TestFile", "The File doesn't not exist.")
        }
        return list
    }

}