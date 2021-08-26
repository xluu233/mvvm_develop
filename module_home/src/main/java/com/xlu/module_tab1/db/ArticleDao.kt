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

    /*获取全部数据*/
    @Query("select * from home_article")
    fun getAllData():List<Article>

    /*删除全部数据*/
    @Query("DELETE FROM home_article")
    fun deleteAll()

    /*删除单条、多条数据*/
    @Delete
    fun delete(vararg articles: Article):Int

    /*插入数据*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg articles: Article)

    /*更新数据*/
    @Update
    fun update(article: Article)

}