package com.xlu.module_ui.db.convertor

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.xlu.module_ui.bean.Article

/**
 * @ClassName ArticleInfoTypeConvertor
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/24 17:43
 */
class ArticleInfoTypeConvertor {

    @TypeConverter
    fun stringToObject(value: String): List<Article.ArticleInfo> {
        val listType = object : TypeToken<List<Article.ArticleInfo>>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun objectToString(list: List<Article.ArticleInfo>): String {
        return Gson().toJson(list)
    }

}