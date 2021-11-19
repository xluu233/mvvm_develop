package com.example.baselibrary.utils.media

import android.util.Log
import java.io.*

/**
 * @ClassName TextFileUtil
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/20 11:46
 */
object TextFileUtil {

    //读取指定目录下的所有TXT文件的文件内容
    @Synchronized fun readTxtFile(file: File): String {
        var content = ""
        if (!file.isDirectory) {  //检查此路径名的文件是否是一个目录(文件夹)
            if (file.name.endsWith("txt")) { //文件格式为""文件
                try {
                    val instream: InputStream = FileInputStream(file)
                    val inputreader = InputStreamReader(instream, "UTF-8")
                    val buffreader = BufferedReader(inputreader)
                    var line = ""
                    //分行读取
                    while (buffreader.readLine()?.also { line = it } != null) {
                        content += """
                        $line
                        
                        """.trimIndent()
                    }
                    instream.close() //关闭输入流
                } catch (e: IOException) {
                    Log.d("TestFile", "The File doesn't not exist.")
                }
            }
        }
        return content
    }

    // 将字符串写入到文本文件中
    @Synchronized fun writeTxtToFile(str: String, file: File) {
        // 每次写入时，都换行写
        val strContent = str+"\r\n"
        try {
            val raf = RandomAccessFile(file, "rwd")
            raf.seek(file.length())
            raf.write(strContent.toByteArray())
            raf.close()
        } catch (e: Exception) {
            Log.e("TestFile", "Error on write File:$e")
        }
    }

    @Synchronized fun writeTxtToFile(strList: List<String>, file: File) {
        var str = ""
        strList.forEach {
            str = it+"\r\n"
        }
        try {
            val raf = RandomAccessFile(file, "rwd")
            raf.seek(file.length())
            raf.write(str.toByteArray())
            raf.close()
        } catch (e: Exception) {
            Log.e("TestFile", "Error on write File:$e")
        }
    }



}