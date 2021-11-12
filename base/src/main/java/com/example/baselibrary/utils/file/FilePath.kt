package com.example.baselibrary.utils.other

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.FileUtils
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.core.content.FileProvider
import com.example.baselibrary.utils.activity.application
import java.io.*
import kotlin.concurrent.thread

/**
 * @ClassName FileUtil
 * @Description 文件工具类，包括获取文件目录，文件格式转换
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/4/15 10:54
 */

fun getContext():Context{
    return application
}

object FilePath {

    /*----------------------外部：分区存储目录------------------------*/
    /**
     *  分区存储-Cache目录
     */
    fun getAppExternalCachePath(subDir: String?=null):String{
        val path = StringBuilder(getContext().externalCacheDir?.absolutePath)
        subDir?.let {
            path.append(File.separator).append(it).append(File.separator)
        }
        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }

    /**
     *  分区存储-File目录
     */
    fun getAppExternalFilePath(subDir: String?=null):String{
        val path = getContext().getExternalFilesDir(subDir)?.absolutePath
        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }
    /*--------------------------------------------------*/


    /*-----------------------内部：私有目录--------------------------*/

    /**
     *  私有目录-files
     */
    fun getAppFilePath(subDir:String?=null): String {
        val path = StringBuilder(getContext().filesDir.absolutePath)
        subDir?.let {
            path.append(File.separator).append(it).append(File.separator)
        }
        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }

    /**
     * 私有目录-cache
     */
    fun getAppCachePath(subDir:String?=null):String{
        val path = StringBuilder(getContext().cacheDir.absolutePath)
        subDir?.let {
            path.append(File.separator).append(it).append(File.separator)
        }
        val dir = File(path.toString())
        if (!dir.exists()) dir.mkdir()
        return path.toString()
    }

    /*------------------------cache子目录------------------------*/
    fun getAudioPathEndWithSeparator(): String {
        return getAppCachePath("audio")
    }

    fun getTxtPathEndWithSeparator(): String {
        return getAppCachePath("txt")
    }

    fun getMp3PathEndWithSeparator(): String {
        return getAppCachePath("mp3")
    }

    fun getTempPathEndWithSeparator(): String {
        return getAppCachePath("temp")
    }

    fun getXmlPathEndWithSeparator(): String {
        return getAppCachePath("xml")
    }
    /*--------------------------------------------------*/



    /*-----------------外部：公共目录（需要权限）----------------*/
    /**
     *  Pictures
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
     *  Download
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
     *  DCIM
     */
    fun getExternalCameraPath( subDir:String?=null): String{
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
     *  Music
     */
    fun getExternalMusicPath(subDir:String?=null): String{
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
    /*---------------------------------------------------------*/


}

object FileUtil{

    /**
     *  File转Uri
     */
    fun file2Uri( file: File?): Uri?{
        if (file==null) return null

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
            FileProvider.getUriForFile(getContext(), "${getContext().packageName}.fileProvider", file)
        } else {
            Uri.fromFile(file)
        }
    }


    /**
     *  将文件转换成byte数组
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


    /**
     *  Uri转File
     */
    fun uri2File(uri: Uri?): File? {
        if (uri==null) return null
        var file:File ?= File(uri.toString())
        if (file!=null && file.exists()) return file


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            when (uri.scheme) {
                ContentResolver.SCHEME_FILE -> {
                    file = File(requireNotNull(uri.path))
                }
                ContentResolver.SCHEME_CONTENT -> {
                    //把文件保存到沙盒
                    val contentResolver = getContext().contentResolver
                    val displayName = "${TimeUtil.getCurrentTime()}.${
                        MimeTypeMap.getSingleton().getExtensionFromMimeType(
                            contentResolver.getType(uri)
                        )
                    }".replace(".bin","")
                    val ios = contentResolver.openInputStream(uri)
                    if (ios != null) {
                        file = File(FilePath.getTxtPathEndWithSeparator(), displayName).apply {
                            val fos = FileOutputStream(this)
                            FileUtils.copy(ios, fos)
                            fos.close()
                            ios.close()
                        }
                    }
                }
                else -> {

                }
            }
            return file
        }else{
            var path: String? = null
            when(uri.scheme){
                "file" -> {
                    path = uri.encodedPath
                    if (path != null) {
                        path = Uri.decode(path)
                        val cr = getContext().contentResolver
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
                        if (index == 0) {
                        } else {
                            val u = Uri.parse("content://media/external/images/media/$index")
                            println("temp uri is :$u")
                        }
                    }
                }
                "content" -> {
                    // 4.2.2以后
                    val proj = arrayOf(MediaStore.Images.Media.DATA)
                    val cursor: Cursor? = getContext().contentResolver.query(uri, proj, null, null, null)
                    cursor?.let {
                        if (cursor.moveToFirst()) {
                            val columnIndex: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                            path = cursor.getString(columnIndex)
                        }
                        cursor.close()
                    }
                }
                else -> {
                    //Log.i(TAG, "Uri Scheme:" + uri.getScheme());
                }
            }
            return File(path)
        }
    }

    /**
     * 删除文件夹
     */
    fun deleteRecursive(fileOrDirectory: File) {
        if (fileOrDirectory.isDirectory) for (child in fileOrDirectory.listFiles()) deleteRecursive(
            child
        )
        fileOrDirectory.delete()
    }

    fun deleteCacheDir() = thread{
        File(FilePath.getTempPathEndWithSeparator()).deleteRecursively()
        File(FilePath.getMp3PathEndWithSeparator()).deleteRecursively()
        File(FilePath.getTxtPathEndWithSeparator()).deleteRecursively()
        File(FilePath.getAudioPathEndWithSeparator()).deleteRecursively()
        File(FilePath.getXmlPathEndWithSeparator()).deleteRecursively()
    }

}
