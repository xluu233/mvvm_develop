package com.example.baselibrary.utils.media

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore


/**
 * @ClassName MediaStoreUtil
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/23 9:52
 */
object MediaStoreUtil {

    /**
     *  查询相册图片
     *
     * @param context
     */
    fun getAlbumList(context: Context):List<Uri>{
        val list= mutableListOf<Uri>()
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            "${MediaStore.MediaColumns.DATE_ADDED} desc"
        )
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
                val uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                list += uri
            }
            cursor.close()
        }
        return list
    }


    fun getVideoList(context: Context){
        val project = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,  //视频的时长
            MediaStore.Video.Media.DATE_MODIFIED
        )

        val cursor: Cursor ?= context.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            project,
            MediaStore.Video.Media.MIME_TYPE + "=?",
            arrayOf("video/mp4"),  //这里只查了mp4格式
            MediaStore.Video.Media.DATE_MODIFIED + " desc"
        )
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
                val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                println("image uri is $uri")
            }
            cursor.close()
        }
    }


}