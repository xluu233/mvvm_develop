package com.example.mvvm_develop

import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.BaseApp
import com.example.baselibrary.db.MMKVUtil
import com.example.baselibrary.utils.log.xLog
import com.example.xlulibrary.ToastBox

/**
 * @ClassName App
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/26 17:09
 */
class App : BaseApp(){


    override fun onCreate() {
        super.onCreate()
        xLog.d("App_init")

        MMKVUtil.init(this)
        initARouter()

        ToastBox.init(
            x = 0, y= 250,
            anim = R.style.ToastAnim_MIUI
        )
    }


    private fun initARouter() {
        //if (DEBUG) return
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this)
    }


}