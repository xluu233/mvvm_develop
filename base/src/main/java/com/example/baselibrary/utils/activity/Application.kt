package com.example.baselibrary.utils.activity

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.core.content.pm.PackageInfoCompat
import com.example.baselibrary.AppInit
import com.example.baselibrary.lifecycle.ActivityStack

val application: Application get() = AppInit.application

val context:Context get() = ActivityStack.currentActivity?.context ?: application

inline val packageName: String get() = application.packageName

inline val activitiesPackageInfo: PackageInfo get() = application.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)

inline val appName: String get() = application.applicationInfo.loadLabel(application.packageManager).toString()

inline val appVersionName: String get() = activitiesPackageInfo.versionName

inline val appVersionCode: Long get() = PackageInfoCompat.getLongVersionCode(activitiesPackageInfo)

inline val isAppDebug: Boolean get() = application.packageManager.getApplicationInfo(packageName, 0).flags and ApplicationInfo.FLAG_DEBUGGABLE != 0

inline val isAppDarkMode: Boolean get() = (application.resources.configuration.uiMode and UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES
