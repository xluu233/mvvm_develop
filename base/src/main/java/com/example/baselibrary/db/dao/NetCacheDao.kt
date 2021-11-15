package com.example.baselibrary.db.dao

import androidx.room.*
import com.example.baselibrary.db.entity.NetCache
/**
 * @ClassName ArticleDao
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/24 14:50
 */
@Dao
interface NetCacheDao {

    /*获取全部数据*/
    @Query("select * from NetCache")
    fun getAllData():List<NetCache>?

    /*删除全部数据*/
    @Query("DELETE FROM NetCache")
    fun deleteAll()

    /*删除单条、多条数据*/
    @Delete()
    fun delete(vararg netCache: NetCache):Int

    /*根据id删除*/
    @Query("DELETE FROM NetCache where md =:md")
    fun delete(md:String)

    /*查询单组数据*/
    @Query("SELECT * FROM NetCache WHERE md = :md")
    fun query(md:String):NetCache?

    /*查询多组数据*/
    @Query("select * from NetCache where md in (:mds)")
    fun query(mds: List<String>): List<NetCache>?

    /*插入单组数据*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(netCache: NetCache)


}