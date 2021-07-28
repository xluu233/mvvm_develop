package com.example.baselibrary.common

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.baselibrary.BaseApp

private var oldMsg: String? = null
private var time: Long = 0


fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(content)){
        return
    }else{
        if (content != oldMsg) {
            // 当显示的内容不一样时，即断定为不是同一个Toast
            Toast.makeText(this, content, duration).show()
            time = System.currentTimeMillis()
        } else {
            // 显示内容一样时，只有间隔时间大于3秒时才显示
            if (System.currentTimeMillis() - time > 3000) {
                Toast.makeText(this, content, duration).show()
                time = System.currentTimeMillis()
            }
        }
        oldMsg = content
    }
}

fun Context.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), duration)
}

fun toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(content))return
    BaseApp.getContext().toast(content, duration)
}

fun toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    BaseApp.getContext().toast(id, duration)
}

fun longToast(content: String, duration: Int = Toast.LENGTH_LONG) {
    if (TextUtils.isEmpty(content))return
    BaseApp.getContext().toast(content, duration)
}

fun longToast(@StringRes id: Int, duration: Int = Toast.LENGTH_LONG) {
    BaseApp.getContext().toast(id, duration)
}


