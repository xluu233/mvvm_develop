package com.xlu.common.login


/**
 * @ClassName LoginInterface
 * @Description login module向外暴露的服务
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/9 15:57
 */
interface LoginInterface {

    fun getToken():String

    fun getUserName():String

    fun getUserID():Int

}