package com.example.baselibrary.base

import com.example.baselibrary.http.ApiResponse
import com.example.baselibrary.http.NetState
import kotlinx.coroutines.*
import java.lang.Exception

/**
 * @ClassName BaseRepository
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/12 10:03
 */
open class BaseRepository(private val coroutineScope: CoroutineScope) {


    protected fun <T> launch(
            block : suspend () -> ApiResponse<T>,
            success: suspend (ApiResponse<T>) -> Unit
    ){
        coroutineScope.launch(Dispatchers.IO){
            var baseResp = ApiResponse<T>()
            try {
                baseResp.state = NetState.STATE_LOADING
                //开始请求数据
                val invoke = block.invoke()
                //将结果复制给baseResp
                baseResp = invoke
                when(baseResp.code){
                    0,200 -> {
                        //请求成功，判断数据是否为空
                        if (baseResp.data == null || baseResp.data is List<*> && (baseResp.data as List<*>).size == 0) {
                            //: 数据为空,结构变化时需要修改判空条件
                            baseResp.state = NetState.STATE_EMPTY
                        } else {
                            //请求成功并且数据为空的情况下，为STATE_SUCCESS
                            baseResp.state = NetState.STATE_SUCCESS
                        }
                    }
                    400,401 -> {
                        baseResp.state = NetState.STATE_FAILED
                    }
                }
            }catch (e:Exception){
                baseResp.state = NetState.STATE_ERROR
                e.printStackTrace()
            }finally {
                success(baseResp)
            }
        }
    }

}