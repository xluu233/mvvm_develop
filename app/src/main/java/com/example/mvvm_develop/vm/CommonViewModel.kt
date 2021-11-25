package com.example.mvvm_develop.vm

import android.util.Log
import com.example.baselibrary.utils.other.TimeUtil
import com.example.baselibrary.vm.BaseViewModel
import com.example.mvvm_develop.R
import com.example.module_community.bean.Community

class CommonViewModel : BaseViewModel() {


    fun test(){
        launchMain {
            println("test launch main")
        }
        launchIO {
            println("test launch IO")
        }
    }



}