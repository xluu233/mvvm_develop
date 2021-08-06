package com.example.baselibrary.http

import androidx.lifecycle.MutableLiveData

/**
 * @ClassName ResultLiveData
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/18 17:01
 */
class ResultLiveData<T> : MutableLiveData<ApiResponse<T>>() {
}