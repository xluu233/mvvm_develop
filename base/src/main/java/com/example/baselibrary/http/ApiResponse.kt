package com.example.baselibrary.http

import java.io.Serializable


class ApiResponse<T> : Serializable {

    var code : Int = -1

    var data : T ?= null
        get() {
            when (code) {
                0, 200 -> {
                    //请求成功
                    return field
                }
                401 -> {

                }
                500 ->{

                }
                else -> {

                }
            }
            throw ApiException(message as String, code)
        }
        set(value) {
            field = value
        }



    var message : Any ?= null

    var netState : NetState = NetState.STATE_UNSTART

    var state:Int ?= 0

    var error : String ?= null

}
