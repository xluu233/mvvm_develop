package com.example.mvvm_develop.bean

import android.content.Context
import com.alibaba.android.arouter.facade.service.SerializationService
import java.io.Serializable
import java.lang.reflect.Type

/**
 * @ClassName RouterBean
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/9 14:04
 */
data class RouterBeanSerializable(val id:Int, val name:String):Serializable
