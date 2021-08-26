package com.xlu.module_tab1.db

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.baselibrary.BaseApp
import com.xlu.module_tab1.bean.Article


/**
 * @ClassName Database
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/24 14:52
 */
@androidx.room.Database(entities = arrayOf(Article::class),version = 2)
abstract class HomeDatabase : RoomDatabase(){

    companion object {

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //此处对于数据库中的所有更新都需要写下面的代码
                database.execSQL(
                    "ALTER TABLE users "
                            + " ADD COLUMN last_update INTEGER"
                )
            }
        }

        const val dbName = "Home.db"
        @Volatile
        private var INSTANCE: HomeDatabase? = null

        fun getInstance(): HomeDatabase = INSTANCE ?: synchronized(this) {
            buildDatabase().also { INSTANCE = it }
        }

        private fun buildDatabase() =
            Room.databaseBuilder(
                BaseApp.getContext(),
                HomeDatabase::class.java, dbName)
                .addMigrations(MIGRATION_1_2)
                .build()
    }




    abstract fun homeDao():ArticleDao


}