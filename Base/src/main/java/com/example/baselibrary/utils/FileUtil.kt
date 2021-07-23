package com.example.baselibrary.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.*


/**
 * @ClassName FileUtil
 * @Description 文件工具类，包括获取文件目录，文件格式转换
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/12 16:11
 */
object FileUtil {

    /**
     * TODO 私有目录-cache
     * @param context
     * @param subDir 子目录文件夹名称
     * @return
     */
    fun getAppCachePath(context: Context, subDir:String?=null):String{
        val path = StringBuilder(context.cacheDir.absolutePath)
        subDir?.let {
            path.append(File.separator).append(it).append(File.separator)
        }
        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }


    /**
     * TODO 私有目录-files
     * @param context
     * @param subDir 子目录文件夹名称
     * @return
     */
    fun getAppFilePath(context: Context, subDir:String?=null): String {
        val path = StringBuilder(context.filesDir.absolutePath)
        subDir?.let {
            path.append(File.separator).append(it).append(File.separator)
        }
        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }


    /**
     * TODO 外部目录-Pictures
     * @param subDir 子目录文件夹名称
     * @return
     */
    fun getExternalPicturesPath(subDir:String?=null): String{
        val path = StringBuilder(Environment.getExternalStorageDirectory().absolutePath)
            .append(File.separator)
            .append(Environment.DIRECTORY_PICTURES)
        subDir?.let {
            path.append(File.separator).append(it).append(File.separator)
        }
        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }

    /**
     * TODO 外部目录-Download
     * @param subDir 子目录文件夹名称
     * @return
     */
    fun getExternalDownloadPath(subDir:String?=null): String{
        val path = StringBuilder(Environment.getExternalStorageDirectory().absolutePath)
            .append(File.separator)
            .append(Environment.DIRECTORY_DOWNLOADS)
        subDir?.let {
            path.append(File.separator).append(it).append(File.separator)
        }
        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }

    /**
     * TODO 外部目录-DCIM
     * @param subDir 子目录文件夹名称
     * @return
     */
    fun getExternalCameraPath(context: Context, subDir:String?=null): String{
        val path = StringBuilder(Environment.getExternalStorageDirectory().absolutePath)
            .append(File.separator)
            .append(Environment.DIRECTORY_DCIM)
        subDir?.let {
            path.append(File.separator).append(it).append(File.separator)
        }
        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }

    /**
     * TODO 外部目录-Music
     * @param subDir 子目录文件夹名称
     * @return
     */
    fun getExternalMusicPath(context: Context, subDir:String?=null): String{
        val path = StringBuilder(Environment.getExternalStorageDirectory().absolutePath)
            .append(File.separator)
            .append(Environment.DIRECTORY_MUSIC)
        subDir?.let {
            path.append(File.separator).append(it).append(File.separator)
        }
        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }

    /**
     * TODO 内部存储-临时目录，cache -> temp
     */
    fun getAppTempPath(context: Context):String{
        val path = StringBuilder(context.cacheDir.absolutePath)
            .append(File.separator)
            .append("temp")
            .append(File.separator)

        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }

    /**
     * TODO 分区存储-Cache目录
     * @param context
     * @param subDir 子目录文件夹名称
     * @return
     */
    fun getExternalAppCachePath(context: Context,subDir: String?=null):String{
        val path = StringBuilder(context.externalCacheDir?.absolutePath)
        subDir?.let {
            path.append(File.separator).append(it).append(File.separator)
        }
        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }

    /**
     * TODO 分区存储-File目录
     * @param context
     * @param subDir 子目录文件夹名称
     * @return
     */
    fun getExternalAppFilePath(context: Context,subDir: String?=null):String{
        val path = context.getExternalFilesDir(subDir)?.absolutePath
        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }


    /**
     * TODO Uri转File
     */
    fun uri2File(context: Context, uri: Uri): File? {
        var path: String ?= null
        when(uri.scheme){
            "file" -> {
                path = uri.encodedPath
                if (path != null) {
                    path = Uri.decode(path)
                    val cr = context.contentResolver
                    val buff = StringBuffer()
                    buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'$path'").append(")")
                    val cur: Cursor? = cr.query(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        arrayOf(
                            MediaStore.Images.ImageColumns._ID,
                            MediaStore.Images.ImageColumns.DATA
                        ),
                        buff.toString(),
                        null,
                        null
                    )
                    var index = 0
                    var dataIdx = 0
                    cur?.let {
                        cur.moveToFirst()
                        while (!cur.isAfterLast()) {
                            index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID)
                            index = cur.getInt(index)
                            dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                            path = cur.getString(dataIdx)
                            cur.moveToNext()
                        }
                        cur.close()
                    }
                    if (index != 0) {
                        val u = Uri.parse("content://media/external/images/media/$index")
                        println("temp uri is :$u")
                    }
                }
            }
            "content" -> {
                // 4.2.2以后
                val proj = arrayOf(MediaStore.Images.Media.DATA)
                val cursor: Cursor? = context.contentResolver.query(uri, proj, null, null, null)
                cursor?.let {
                    if (cursor.moveToFirst()) {
                        val columnIndex: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                        path = cursor.getString(columnIndex)
                    }
                    cursor.close()
                }
            }
            else -> {
                return null
            }
        }
        return if (path.isNullOrEmpty()){
            null
        }else{
            File(path)
        }
    }

    /**
     * TODO File转Uri
     */
    fun file2Uri(context: Context, file: File?):Uri?{
        if (file==null) return null

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
            FileProvider.getUriForFile(context, "${context.packageName}.fileProvider", file)
        } else {
            Uri.fromFile(file)
        }
    }


    /**
     * TODO 将文件转换成byte数组
     */
    fun file2Byte(file: File?): ByteArray? {
        if (file==null) return null

        var buffer: ByteArray? = null
        try {
            val fis = FileInputStream(file)
            val bos = ByteArrayOutputStream()
            val b = ByteArray(1024)
            var n: Int
            while (fis.read(b).also { n = it } != -1) {
                bos.write(b, 0, n)
            }
            fis.close()
            bos.close()
            buffer = bos.toByteArray()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return buffer
    }

}