package com.example.mvvm_develop

import androidx.lifecycle.MutableLiveData
import com.example.baselibrary.base.BaseRepository
import com.example.baselibrary.http.ApiResponse
import com.example.baselibrary.http.ResultLiveData
import com.example.mvvm_develop.api.Api
import com.example.baselibrary.http.RetrofitManager
import com.xlu.common.bean.BannerData
import kotlinx.coroutines.CoroutineScope

/**
 * @ClassName CommonRepo
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/12 10:18
 */
class CommonRepo(scope: CoroutineScope) : BaseRepository(scope) {

    private var mService = RetrofitManager.getApiService(Api::class.java)

    fun laod(resultLiveData: ResultLiveData<List<BannerData>>){
        launch(
            block = {
                mService.loadProjectTree()
            },
            response = {
                resultLiveData.postValue(it)
            }
        )
    }

    fun laod2(resultLiveData: MutableLiveData<ApiResponse<List<BannerData>>>){
        launch(
            block = {
                mService.loadProjectTree()
            },
            response = {
                resultLiveData.postValue(it)
            }
        )
    }

}