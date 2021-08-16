package com.example.mvvm_develop

import androidx.lifecycle.*
import androidx.navigation.NavController
import com.example.baselibrary.base.BaseViewModel
import com.example.baselibrary.http.ResultLiveData
import com.example.mvvm_develop.bean.BannerData

class CommonViewModel : BaseViewModel() {

    private val repo by lazy { CommonRepo(viewModelScope) }

    var currentNavController : LiveData<NavController> ?= null

    val liveData = ResultLiveData<List<BannerData>>()
    fun load(){
        repo.laod(liveData)
    }


}