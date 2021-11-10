package com.example.mvvm_develop

import androidx.lifecycle.*
import com.example.baselibrary.base.BaseViewModel
import com.example.baselibrary.http.ResultLiveData
import com.xlu.common.bean.BannerData

class CommonViewModel : BaseViewModel() {

    private val repo by lazy { CommonRepo(viewModelScope) }


    val liveData = ResultLiveData<List<BannerData>>()
    fun load(){
        repo.load2(liveData)
    }


}