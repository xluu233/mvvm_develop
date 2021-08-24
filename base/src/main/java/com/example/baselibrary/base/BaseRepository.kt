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
        block: suspend () -> ApiResponse<T>,
        response: suspend (ApiResponse<T>) -> Unit,
        db: (suspend (ApiResponse<T>) -> Unit) ?= null
    ){
        coroutineScope.launch(Dispatchers.IO){
            var result = ApiResponse<T>()
            try {
                result.state = NetState.STATE_LOADING
                //开始请求数据
                val invoke = block.invoke()
                //将结果复制给baseResp
                result = invoke
                when(result.code){
                    0,200 -> {
                        //请求成功，判断数据是否为空
                        if (result.data == null || result.data is List<*> && (result.data as List<*>).size == 0) {
                            //数据为空,结构变化时需要修改判空条件
                            result.state = NetState.STATE_EMPTY
                        } else {
                            //请求成功并且数据为空的情况下，为STATE_SUCCESS
                            result.state = NetState.STATE_SUCCESS
                        }
                    }
                    400,401 -> {
                        result.state = NetState.STATE_FAILED
                    }
                }
            }catch (e:Exception){
                result.state = NetState.STATE_ERROR
                e.printStackTrace()
            }finally {
                response(result)
            }
        }
    }

}