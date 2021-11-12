package com.example.baselibrary.db

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.baselibrary.db.dao.NetCacheDao
import com.example.baselibrary.db.entity.NetCache
import com.example.baselibrary.utils.activity.application


/**
 * @ClassName Database
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/24 14:52
 */
@androidx.room.Database(entities = arrayOf(NetCache::class),version = 1)
abstract class BaseDatabase : RoomDatabase(){

    companion object {

        const val dbName = "Base.db"

        @Volatile
        private var INSTANCE: BaseDatabase? = null

        fun getInstance(): BaseDatabase = INSTANCE ?: synchronized(this) {
            buildDatabase().also { INSTANCE = it }
        }

        private fun buildDatabase() =
            Room.databaseBuilder(application, BaseDatabase::class.java, dbName).build()
    }


    abstract fun netCacheDao():NetCacheDao

}