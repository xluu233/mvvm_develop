package com.example.mvvm_develop

import com.example.baselibrary.base.BaseRepository
import com.example.baselibrary.http.ResultLiveData
import com.example.mvvm_develop.api.Api
import com.example.mvvm_develop.api.RetrofitManager
import com.example.mvvm_develop.bean.BannerData
import kotlinx.coroutines.CoroutineScope

/**
 * @ClassName CommonRepo
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/12 10:18
 */
class CommonRepo(scope: CoroutineScope) : BaseRepository(scope) {

    private var mService: Api = RetrofitManager.getApiService(Api::class.java)

    fun laod(resultLiveData: ResultLiveData<List<BannerData>>){
        launch(
            block = {
                mService.loadProjectTree()
            },
            success = {
                resultLiveData.postValue(it)
            }
        )
    }


}