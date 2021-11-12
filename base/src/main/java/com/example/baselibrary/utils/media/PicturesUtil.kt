package com.example.baselibrary.utils.media

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.example.baselibrary.utils.other.BitmapUtil
import java.io.*


/**
 * 图片操作工具类
 */
object PicturesUtil {

    const val pic_save_path = "ABC啦啦啦"


    /**
     *  Bitmap保存为图片
     * @param context
     * @param bitmap 输入源
     * @param file 输出文件
     * @param refreshAlbum 是否刷新相册，刷新将会复制源文件到 SDcard->Pitures 目录并删除源文件
     */
    fun saveBitmap2File(context: Context?=null, bitmap: Bitmap?, file: File, refreshAlbum:Boolean = false) {
        try {
            val fos = FileOutputStream(file)
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (refreshAlbum) context?.let {
                refreshSystemAlbum(it,file)
            }
        }
    }

    /**
     *  网络url保存为图片
     */
    fun saveUrl2File(url:String?,file: File){
        val bitmap = BitmapUtil.getUrlBitmap(url)
        saveBitmap2File(bitmap = bitmap,file = file)
    }

    /**
     *  通知系统相册更新
     */
    private fun refreshSystemAlbum(context: Context, file: File) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            insertPicInAndroidQ(context, file,deleteSource = true)
        } else {
            //通知系统图库更新
            val value = ContentValues()
            value.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            value.put(MediaStore.Images.Media.DATA, file.absolutePath)
            context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, value)
        }
    }

    /**
     *  复制文件
     * @param source 输入文件
     * @param target 输出文件
     */
    fun copyFile(source: File?, target: File?) {
        var fileInputStream: FileInputStream? = null
        var fileOutputStream: FileOutputStream? = null
        try {
            fileInputStream = FileInputStream(source)
            fileOutputStream = FileOutputStream(target)
            val buffer = ByteArray(1024)
            while (fileInputStream.read(buffer) > 0) {
                fileOutputStream.write(buffer)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                source?.delete()
                fileInputStream?.close()
                fileOutputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     *  Android Q以后向系统相册插入图片
     * 注意：这种方式将会复制源文件到 Picture/SAVE_PATH 目录下，可以选择在插入之后删除源文件
     *  @param deleteSource 是否删除源文件
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun insertPicInAndroidQ(context: Context, file: File, deleteSource:Boolean) {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DESCRIPTION, file.name)
        values.put(MediaStore.Images.Media.DISPLAY_NAME, file.name)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        values.put(MediaStore.Images.Media.TITLE, "Image.jpg")
        values.put(MediaStore.Images.Media.RELATIVE_PATH, getMediaStorePath())
        //"Pictures/${pic_save_path}/"

        val external = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val resolver: ContentResolver = context.contentResolver
        val insertUri = resolver.insert(external, values)
        val inputStream: BufferedInputStream?
        var os: OutputStream? = null
        try {
            inputStream = BufferedInputStream(FileInputStream(file))
            if (insertUri != null) {
                os = resolver.openOutputStream(insertUri)
            }
            if (os != null) {
                val buffer = ByteArray(1024 * 4)
                var len: Int
                while (inputStream.read(buffer).also { len = it } != -1) {
                    os.write(buffer, 0, len)
                }
                os.flush()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            os?.close()
            if (deleteSource) file.delete()
        }
    }

    private fun getMediaStorePath(): String {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            // full path
            "${Environment.getExternalStorageDirectory().absolutePath}/" + "${Environment.DIRECTORY_PICTURES}/$pic_save_path/"
        } else {
            // relative path
            "${Environment.DIRECTORY_PICTURES}/$pic_save_path/"
        }
    }

}