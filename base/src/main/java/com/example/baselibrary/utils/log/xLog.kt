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

    private val logFile:File by lazy {
        File(FilePath.getAppFilePath("log"),"${TimeUtil.getCurrentTime()}.txt")
    }

    fun init(logName: String, showLog: Boolean) {
        LogName = logName
        xLog.showLog = showLog
        saveLog("初始化", TimeUtil.getCurrentTime())
    }

    fun d(TAG: String, content: String?) {
        if (showLog && content!=null) {
            Log.d("[$LogName]$TAG", content)
        }
        saveLog("[$LogName]", content)
    }

    fun v(TAG: String, content: String?) {
        if (showLog && content!=null) {
            Log.v("[$LogName]$TAG", content)
        }
        saveLog("[$LogName]", content)
    }

    fun w(TAG: String, content: String?) {
        if (showLog && content!=null) {
            Log.w("[$LogName]$TAG", content)
        }
        saveLog("[$LogName]", content)
    }

    fun e(TAG: String, content: String?) {
        if (showLog && content!=null) {
            Log.e("[$LogName]$TAG", content)
        }
        saveLog("[$LogName]", content)
    }

    fun i(TAG: String, content: String?) {
        if (showLog && content!=null) {
            Log.i("[$LogName]$TAG", content)
        }
        saveLog("[$LogName]", content)
    }

    fun d(content: String?) {
        if (showLog && content!=null) {
            Log.d("[$LogName]", content)
        }
        saveLog("[$LogName]", content)
    }

    fun v(content: String?) {
        if (showLog && content!=null) {
            Log.v("[$LogName]", content)
        }
        saveLog("[$LogName]", content)
    }

    fun w(content: String?) {
        if (showLog && content!=null) {
            Log.w("[$LogName]", content)
        }
        saveLog("[$LogName]", content)
    }

    fun e(content: String?) {
        if (showLog && content!=null) {
            Log.e("[$LogName]", content)
        }
        saveLog("[$LogName]", content)
    }

    fun i(content: String?) {
        if (showLog && content!=null) {
            Log.i("[$LogName]", content)
        }
        saveLog("[$LogName]", content)
    }

    fun saveLog(tag:String?="[$LogName]", content:String?){
        if (content==null) return
        str.append(tag).append(": ").append(content).append("\n")
    }

    fun saveTxt(){
        TextFileUtil.writeTxtToFile(str.toString(), logFile)
        str.clear()
    }

}