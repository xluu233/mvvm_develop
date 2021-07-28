package com.example.mvvm_develop.ui.databinding

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baselibrary.base.BaseViewModel
import com.example.baselibrary.log.xLog

/**
 * @ClassName DataBindViewModel
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/27 16:21
 */
class DataBindViewModel : BaseViewModel() {

    val _text =  MutableLiveData<Int>(0)

    val xx = ObservableField<String>()


    fun add(){
        xx.set(System.currentTimeMillis().toString())
        _text.value = (_text.value ?:0) + 10
        xLog.d(_text.value.toString())
    }


}