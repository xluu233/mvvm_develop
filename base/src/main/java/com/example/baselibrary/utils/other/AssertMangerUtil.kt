package com.example.baselibrary.utils.other

import android.content.Context
import com.example.baselibrary.utils.log.xLog
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

/**
 * @ClassName AssertMangerUtil
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/23 9:23
 */
object AssertMangerUtil {

    /**
     * 拷贝asset文件到指定路径，可变更文件名
     *
     * @param context   context
     * @param assetName asset文件
     * @param savePath  目标路径
     * @param saveName  目标文件名
     */
    fun copyFileFromAssets(
        context: Context,
        assetName: String,
        savePath: String,
        saveName: String
    ) {
        // 若目标文件夹不存在，则创建
        val dir = File(savePath)
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                xLog.d("FileUtils", "mkdir error: $savePath")
                return
            }
        }

        // 拷贝文件
        val filename = "$savePath/$saveName"
        val file = File(filename)
        if (!file.exists()) {
            try {
                val inStream: InputStream = context.assets.open(assetName)
                val fileOutputStream = FileOutputStream(filename)
                var byteread: Int
                val buffer = ByteArray(1024)
                while (inStream.read(buffer).also { byteread = it } != -1) {
                    fileOutputStream.write(buffer, 0, byteread)
                }
                fileOutputStream.flush()
                inStream.close()
                fileOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            xLog.d("FileUtils", "[copyFileFromAssets] copy asset file: $assetName to : $filename")
        } else {
            xLog.d("FileUtils", "[copyFileFromAssets] file is exist: $filename")
        }
    }

    /**
     * 拷贝asset目录下所有文件到指定路径
     *
     * @param context    context
     * @param assetsPath asset目录
     * @param savePath   目标目录
     */
    fun copyFilesFromAssets(context: Context, assetsPath: String, savePath: String) {
        try {
            // 获取assets指定目录下的所有文件
            val fileList: Array<String> ?= context.assets.list(assetsPath)
            if (!fileList.isNullOrEmpty()) {
                val file = File(savePath)
                // 如果目标路径文件夹不存在，则创建
                if (!file.exists()) {
                    if (!file.mkdirs()) {
                        xLog.d("FileUtils", "mkdir error: $savePath")
                        return
                    }
                }
                for (fileName in fileList) {
                    copyFileFromAssets(context, "$assetsPath/$fileName", savePath, fileName)
                }
            }
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

}