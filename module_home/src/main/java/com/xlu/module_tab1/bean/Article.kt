package com.xlu.module_tab1.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.xlu.module_tab1.db.convertor.ArticleInfoTypeConvertor
import com.xlu.module_tab1.db.convertor.TagTypeConverter


@Entity(tableName = "home_article")
@TypeConverters(TagTypeConverter::class,ArticleInfoTypeConvertor::class)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var databaseId: Int,


    var curPage: Int,

    var datas: List<ArticleInfo>,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
){

    data class ArticleInfo(
        var apkLink: String,
        var audit: Int,
        var author: String,
        var canEdit: Boolean,
        var chapterId: Int,
        var chapterName: String,
        var collect: Boolean,
        var courseId: Int,
        var desc: String,
        var descMd: String,
        var envelopePic: String,
        var fresh: Boolean,
        var host: String,
        var id: Int,
        var link: String,
        var niceDate: String,
        var niceShareDate: String,
        var origin: String,
        var prefix: String,
        var projectLink: String,
        var publishTime: Long,
        var realSuperChapterId: Int,
        var selfVisible: Int,
        var shareDate: Long,
        var shareUser: String,
        var superChapterId: Int,
        var superChapterName: String,
        var tags: List<Tag>,
        var title: String,
        var type: Int,
        var userId: Int,
        var visible: Int,
        var zan: Int
    ) {
        data class Tag(
            var name: String,
            var url: String
        )
    }
}









