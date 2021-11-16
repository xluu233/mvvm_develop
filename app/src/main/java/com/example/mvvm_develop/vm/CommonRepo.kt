package com.example.mvvm_develop.vm

import com.example.baselibrary.vm.BaseRepository
import kotlinx.coroutines.CoroutineScope

/**
 * @ClassName CommonRepo
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/12 10:18
 */
class CommonRepo(scope: CoroutineScope) : BaseRepository(scope) {

    companion object{
        @Volatile
        private var instance: CommonRepo?= null

        fun getInstance(scope: CoroutineScope) = instance ?: synchronized(this) {
            instance ?: CommonRepo(scope).also { instance = it }
        }
    }

}