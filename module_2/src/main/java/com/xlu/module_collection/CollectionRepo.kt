package com.xlu.module_collection

import com.example.baselibrary.vm.BaseRepository
import kotlinx.coroutines.CoroutineScope

/**
 * @ClassName CommonRepo
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/12 10:18
 */
class CollectionRepo(scope: CoroutineScope) : BaseRepository(scope) {

    companion object{
        @Volatile
        private var instance:CollectionRepo ?= null

        fun getInstance(scope: CoroutineScope) = instance ?: synchronized(this) {
            instance ?: CollectionRepo(scope).also { instance = it }
        }
    }


}