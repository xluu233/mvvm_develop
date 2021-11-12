package com.example.baselibrary.utils.media

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.example.baselibrary.utils.other.FilePath
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import java.io.*


object SavePicUtil {

    const val NAME = "TEST"

    fun savePic(context: Context, picUrl: String){
        Glide.with(context).asBitmap().load(picUrl).into(object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    saveBitAndroidQ(context,resource,"${System.currentTimeMillis()}.png")
                }else{
                    saveBit(context,resource,"${System.currentTimeMillis()}.png")
                }
            }
        })
    }

    fun savePic(context: Context, bitmap: Bitmap){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            saveBitAndroidQ(context,bitmap,"${System.currentTimeMillis()}.png")
        }else{
            saveBit(context,bitmap,"${System.currentTimeMillis()}.png")
        }
    }

    /**
     * TODO 保存图片（只使用于AndroidQ以下）
     */
    private fun saveBit(context: Context, bitmap: Bitmap, fileName: String) {
        val file = File(FilePath.getExternalPicturesPath(NAME), fileName)
        try {
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        //4.通知系统图库更新
        val value = ContentValues()
        value.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        value.put(MediaStore.Images.Media.DATA, file.absolutePath)
        context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, value)
    }


    /**
     * TODO MediaStore插入图片到公共目录(AndroidQ以上版本使用)
     */
    private fun saveBitAndroidQ(context: Context,bitmap: Bitmap,fileName: String) {
        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        val path = getAppPicturePath()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, path)
        } else {
            val fileDir = File(path)
            if (!fileDir.exists()){
                fileDir.mkdir()
            }
            contentValues.put(MediaStore.MediaColumns.DATA, path + "${System.currentTimeMillis()}.png")
        }
        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        uri?.also {
            val outputStream = context.contentResolver.openOutputStream(it)
            outputStream?.also { os ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
                os.close()
            }
        }
    }

    private fun getAppPicturePath(): String {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            // full path
            "${Environment.getExternalStorageDirectory().absolutePath}/" + "${Environment.DIRECTORY_PICTURES}/$NAME/"
        } else {
            // relative path
            "${Environment.DIRECTORY_PICTURES}/$NAME/"
        }
    }


    /**
     * 复制文件
     *
     * @param source 输入文件
     * @param target 输出文件
     */
    fun copy(source: File?, target: File?) {
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
                //source?.delete()
                fileInputStream?.close()
                fileOutputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}