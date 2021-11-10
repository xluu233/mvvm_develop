package com.example.baselibrary.utils

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.example.baselibrary.BaseApp
import com.example.baselibrary.log.xLog

/**
 * @ClassName VersionUtil
 * @Description 版本管理工具
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/10/20 15:46
 */
object VersionUtil {

    fun getAppVersionName(): String {
        var versionName = ""
        try {
            val pm: PackageManager =  BaseApp.getContext().packageManager
            val pi: PackageInfo = pm.getPackageInfo(BaseApp.getContext().packageName, 0)
            versionName = pi.versionName
        } catch (e: Exception) {
            xLog.e("VersionInfo", "Exception")
        }
        return versionName
    }

    fun getAppVersionCode(): Int{
        var versioncode = 0
        try {
            val pm: PackageManager =  BaseApp.getContext().packageManager
            val pi: PackageInfo = pm.getPackageInfo(BaseApp.getContext().packageName, 0)
            versioncode = pi.versionCode
        } catch (e: Exception) {
            xLog.e("VersionInfo", "Exception")
        }
        return versioncode
    }

}