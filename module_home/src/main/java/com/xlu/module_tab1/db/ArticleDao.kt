package com.xlu.module_tab1.db

import androidx.room.*
import com.xlu.module_tab1.bean.Article

/**
 * @ClassName ArticleDao
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/24 14:50
 */
@Dao
interface ArticleDao {

    @Query("select * from home_article")
    fun getAllData():List<Article>

    @Query("DELETE FROM home_article")
    fun deleteAll()

    @Delete
    fun delete(vararg articles: Article):Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg articles: Article)

    @Update
    fun update(article: Article)

}