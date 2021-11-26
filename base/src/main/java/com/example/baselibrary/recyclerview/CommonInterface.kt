package com.example.baselibrary.recyclerview

import android.view.View

/**
 * @ClassName CommonInterface
 * @Description TODO 移花接木
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/26 15:38
 */



interface AdapterClickListener<T>{
    fun click(position:Int, view: View, data:T)
    fun longClick(position:Int, view: View, data:T)
}

