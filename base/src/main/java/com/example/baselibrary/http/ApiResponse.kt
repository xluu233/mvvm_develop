package com.example.baselibrary.http

import java.io.Serializable


class ApiResponse<T> : Serializable {

    var code : Int = 0

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
            }
            throw ApiException(message as String, code)
        }
        set(value) {
            field = value
        }



    var message : Any ?= null

    var state : NetState = NetState.STATE_UNSTART

    var error : String = ""

    /**
     * 如果服务端data肯定不为null，直接将data返回。
     * 假如data为null证明服务端出错,这种错误已经产生并且不可逆，反射生成空对象
     * 客户端只需保证不闪退并给予提示即可
     */
//    fun data(): T? {
//        /*if (null==data){
//            data = Any::class.java.newInstance() as T
//        }*/
//        when (code) {
//            0, 200 -> {
//                //请求成功
//                return data
//            }
//            401 -> {
//
//            }
//            500 ->{
//
//            }
//        }
//        throw ApiException(message as String, code)
//    }

}
