package com.example.mvvm_develop

import androidx.lifecycle.*
import com.example.baselibrary.http.ResultLiveData
import com.example.mvvm_develop.bean.BannerData

class CommonViewModel : ViewModel() {

    private val repo by lazy { CommonRepo(viewModelScope) }

    val liveData = ResultLiveData<List<BannerData>>()
    fun load(){
        repo.laod(liveData)
    }


}