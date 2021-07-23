package com.example.baselibrary.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.baselibrary.http.ApiException


open class BaseViewModel:ViewModel() {

    /**
     * 无数据
     */
    val emptyLiveDate = MutableLiveData<Any>()
}