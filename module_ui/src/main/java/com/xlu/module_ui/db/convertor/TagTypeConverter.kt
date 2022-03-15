package com.xlu.module_ui.db.convertor

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.xlu.module_ui.bean.Article

class TagTypeConverter {
    @TypeConverter
    fun stringToObject(value: String): List<Article.ArticleInfo.Tag> {
        val listType = object : TypeToken<List<Article.ArticleInfo.Tag>>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun objectToString(list: List<Article.ArticleInfo.Tag>): String {
        return Gson().toJson(list)
    }
}
