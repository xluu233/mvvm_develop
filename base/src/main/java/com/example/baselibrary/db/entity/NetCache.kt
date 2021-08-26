package com.example.baselibrary.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @ClassName NetCache
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/25 14:50
 */
@Entity(tableName = "NetCache")
data class NetCache(

    @PrimaryKey
    val md:String,

    val response:String
)
