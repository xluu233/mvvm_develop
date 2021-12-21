package com.example.baselibrary.utils.log

import android.util.Log
import com.example.baselibrary.utils.activity.isAppDebug
import com.example.baselibrary.utils.other.FilePath
import com.example.baselibrary.utils.media.TextFileUtil
import com.example.baselibrary.utils.other.TimeUtil
import java.io.File


object xLog {

    private var LogName = "xLog"
    private var showLog = isAppDebug
    private var str: StringBuilder = StringBuilder("")


    private enum class Level{
        Info,
        Debug,
        Warning,
        Error
    }

    private val logFile:File by lazy {
        File(FilePath.getAppFilePath("log"),"${TimeUtil.getCurrentTime()}.txt")
    }

    fun init(logName: String, showLog: Boolean) {
        LogName = logName
        xLog.showLog = showLog
        saveLog("初始化", TimeUtil.getCurrentTime())
    }


    fun i(content: Any?) {
        if (showLog && content != null) {
            flattenData(level = Level.Info, content = content)
        }
    }

    fun d(content: Any?) {
        if (showLog && content != null) {
            flattenData(level = Level.Debug, content =  content)
        }
    }

    fun w(content: Any?) {
        if (showLog && content != null) {
            flattenData(level = Level.Warning, content = content)
        }
    }

    fun e(content: Any?) {
        if (showLog && content != null) {
            flattenData(level = Level.Error, content = content)
        }
    }


    fun i(TAG: String ?= null, content: Any?) {
        if (showLog && content != null) {
            flattenData(level = Level.Info,TAG, content)
        }
    }

    fun d(TAG: String ?= null, content: Any?) {
        if (showLog && content != null) {
            flattenData(level = Level.Debug,TAG, content)
        }
    }

    fun w(TAG: String ?= null, content: Any?) {
        if (showLog && content != null) {
            flattenData(level = Level.Warning,TAG, content)
        }
    }

    fun e(TAG: String ?= null, content: Any?) {
        if (showLog && content != null) {
            flattenData(level = Level.Error,TAG, content)
        }
    }


    @Synchronized
    fun saveLog(level: String, tag:String ="[$LogName]",content:String = ""){
        str.append(level).append("/").append(tag).append(": ").append(content).append("\n")
        if (str.length > 1000){
            saveTxt()
        }
    }

    @Synchronized
    fun saveTxt(){
        TextFileUtil.writeTxtToFile(str.toString(), logFile)
        str.clear()
    }


    private fun flattenData(level:Level,TAG: String?=null,content:Any) {
        when(content){
            is Collection<*> -> {
                content.forEach {
                    showLog(level = level,TAG = "[$LogName]$TAG",content = it.toString())
                }
            }
            is Map<*, *> -> {
                content.forEach {
                    showLog(level = level,TAG = "[$LogName]$TAG",content = "${it.key}  ${it.value}")
                }
            }
            is String -> {
                showLog(level = level,TAG = "[$LogName]$TAG",content = content)
            }
            else -> {
                showLog(level = level,TAG = "[$LogName]$TAG",content = content.toString())
            }
        }
    }

    private fun showLog(level:Level,TAG: String?=null,content:String = ""){
        when(level){
            Level.Info -> {
                Log.i("[$LogName]$TAG", content)
                saveLog("info","[$LogName]", content)
            }
            Level.Debug -> {
                Log.d("[$LogName]$TAG", content)
                saveLog("debug","[$LogName]", content)
            }
            Level.Warning -> {
                Log.w("[$LogName]$TAG", content)
                saveLog("warn","[$LogName]", content)
            }
            Level.Error -> {
                Log.e("[$LogName]$TAG", content)
                saveLog("error","[$LogName]", content)
            }
        }
    }

}