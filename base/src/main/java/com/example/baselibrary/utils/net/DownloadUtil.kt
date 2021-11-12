package com.example.baselibrary.utils.other

import com.example.baselibrary.utils.log.xLog
import okhttp3.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.TimeUnit


/**
 * @ClassName DownloadUtil
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/14 9:37
 */

interface OnDownloadListener {
    fun onDownloadSuccess(path: String?)
    fun onDownloading(progress: Int)
    fun onDownloadFailed()
}

class DownloadUtil{


    var call:Call ?= null

    companion object{
        private val okHttpClient: OkHttpClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS) //连接超时
                .readTimeout(10, TimeUnit.SECONDS) //读取超时
                .writeTimeout(10, TimeUnit.SECONDS) //写超时
                .build()
        }
    }

    fun download(url: String, file: File, listener: OnDownloadListener?) = apply {
        val request: Request = Request.Builder().url(url).build()
        call = okHttpClient.newCall(request)
        call?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                xLog.d("TAG", "onFailure: ")
                listener?.onDownloadFailed()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                var `is`: InputStream? = null
                val buf = ByteArray(2048)
                var len = 0
                var fos: FileOutputStream? = null
                try {
                    `is` = response.body!!.byteStream()
                    val total = response.body!!.contentLength()
                    fos = FileOutputStream(file)
                    var sum: Long = 0
                    while (`is`.read(buf).also { len = it } != -1) {
                        fos.write(buf, 0, len)
                        sum += len.toLong()
                        val progress = (sum * 1.0f / total * 100).toInt()
                        //下载中
                        listener?.onDownloading(progress)
                    }
                    fos.flush()
                    //下载完成
                    listener?.onDownloadSuccess(file.absolutePath)
                } catch (e: Exception) {
                    xLog.e("下载异常", e.message!!)
                } finally {
                    try {
                        `is`?.close()
                    } catch (e: IOException) {
                    }
                    try {
                        fos?.close()
                    } catch (e: IOException) {
                    }
                }
            }
        })

    }

    fun cancel(){
        call?.cancel()
    }

}