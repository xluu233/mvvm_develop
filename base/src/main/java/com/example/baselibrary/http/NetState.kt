package com.example.baselibrary.http

/**
 * @ClassName NetState
 * @Description 赋值LiveData的网络状态
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/12 11:21
 */
enum class NetState {
    STATE_UNSTART,//未知
    STATE_LOADING,//加载中
    STATE_SUCCESS,//成功
    STATE_EMPTY,//数据为null
    STATE_FAILED,//接口请求成功但是服务器返回error
    STATE_ERROR//请求失败
}