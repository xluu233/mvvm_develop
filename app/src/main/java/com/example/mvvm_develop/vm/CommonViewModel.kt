package com.example.mvvm_develop.vm

import com.example.baselibrary.vm.BaseViewModel

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