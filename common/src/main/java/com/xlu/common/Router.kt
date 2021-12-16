package com.xlu.common

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantParams

/**
 * @ClassName Router
 * @Description TODO
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/11/26 17:33
 */
fun ARouter.transFragmentPage(fragmentPath: String): Postcard {
    return build(ConstantARouter.PoppetActivity)
        .withString(ConstantParams.NavigationDestination, fragmentPath)
}