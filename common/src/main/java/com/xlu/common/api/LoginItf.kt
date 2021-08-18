package com.xlu.common.api

import com.alibaba.android.arouter.facade.template.IProvider


/**
 * @ClassName LoginInterface
 * @Description login module向外暴露的服务
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/9 15:57
 */
interface LoginItf : IProvider{

    fun getToken():String

    fun getUserName():String

    fun getUserID():Int

}