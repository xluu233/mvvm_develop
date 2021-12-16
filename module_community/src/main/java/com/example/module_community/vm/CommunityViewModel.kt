package com.example.module_community.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.baselibrary.bus.LiveDataBus
import com.example.baselibrary.utils.activity.application
import com.example.baselibrary.utils.other.TimeUtil
import com.example.baselibrary.vm.BaseViewModel
import com.example.module_community.R
import com.example.module_community.bean.Community
import com.example.module_community.bean.Image
import com.xlu.common.constants.ConstantEvent
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class CommunityViewModel : BaseViewModel() {

    val lolImageList = arrayListOf<String>()

    suspend fun getImageData():List<Image>{
        val list = mutableListOf<Image>()
        val urlList = readAssert("url.txt")
        lolImageList.clear()
        lolImageList.addAll(urlList)

        for (i in 0 until 100){
            list.add(
                Image(
                    id = i,
                    url = urlList[i],
                    title = TimeUtil.getCurrentTime()
                )
            )
        }
        return list
    }

    private fun readAssert(name:String):List<String>{
        val list = mutableListOf<String>()

        try {
            val inputStream = application.assets.open(name)
            val inputreader = InputStreamReader(inputStream, "UTF-8")
            val buffreader = BufferedReader(inputreader)
            var line = ""
            //分行读取
            while (buffreader.readLine()?.also { line=it } != null) {
                list.add(line)
            }
            inputStream.close()
        } catch (e: IOException) {
            Log.d("TestFile", "The File doesn't not exist.")
        }
        return list
    }



    fun createShareElementData(page:Int):List<Community>{
        val result = mutableListOf<Community>()
        for (i in 0 until 20){
            result.add(Community(name = nameList.random(),subName = TimeUtil.getCurrentTime(),id = page*20 + i,icon = iconList.random(),img = imageList.random(),content = contentList.random()))
        }
        /*result.forEach {
            Log.d("TAG", "createShareElementData: $it")
        }*/
        return result
    }


    private val imageList = arrayListOf(
        arrayListOf(
            "https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d04caa6b4d494f0bb3454833e4710c2f~tplv-k3u1fbpfcp-watermark.image?",
        ),
        arrayListOf(
            "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/811f50072d0f4c6eb38218721361b54e~tplv-k3u1fbpfcp-zoom-mark-crop-v2:460:460:0:0.awebp?",
            "https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8566d324f92e476bb2e07f3c6fe6424c~tplv-k3u1fbpfcp-no-mark:800:0:0:0.awebp?"
        ),
        arrayListOf(
            "https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ddbaeabbdf4a464eae2319c3c12ef014~tplv-k3u1fbpfcp-no-mark:800:0:0:0.awebp?",
            "https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8e61331b172a4d75af7363a993c2fdd9~tplv-k3u1fbpfcp-no-mark:800:0:0:0.awebp?",
            "https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1e8e1c080b0e4f41a539ea358aff1ab6~tplv-k3u1fbpfcp-no-mark:800:0:0:0.awebp?"
        ),
        arrayListOf(
            "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0ffdf4410e17484a8a97ba47b720f21e~tplv-k3u1fbpfcp-zoom-mark-crop-v2:240:240:0:0.awebp?",
            "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/811f50072d0f4c6eb38218721361b54e~tplv-k3u1fbpfcp-zoom-mark-crop-v2:460:460:0:0.awebp?",
            "https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8566d324f92e476bb2e07f3c6fe6424c~tplv-k3u1fbpfcp-no-mark:800:0:0:0.awebp?",
            "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0284e75d61964b228ac8a5e56c857a40~tplv-k3u1fbpfcp-zoom-mark-crop-v2:460:460:0:0.awebp?"
        ),
        arrayListOf(
            "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0ffdf4410e17484a8a97ba47b720f21e~tplv-k3u1fbpfcp-zoom-mark-crop-v2:240:240:0:0.awebp?",
            "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/811f50072d0f4c6eb38218721361b54e~tplv-k3u1fbpfcp-zoom-mark-crop-v2:460:460:0:0.awebp?",
            "https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8566d324f92e476bb2e07f3c6fe6424c~tplv-k3u1fbpfcp-no-mark:800:0:0:0.awebp?",
            "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/811f50072d0f4c6eb38218721361b54e~tplv-k3u1fbpfcp-zoom-mark-crop-v2:460:460:0:0.awebp?",
            "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0284e75d61964b228ac8a5e56c857a40~tplv-k3u1fbpfcp-zoom-mark-crop-v2:460:460:0:0.awebp?"
        ),
        arrayListOf(
            "https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d04caa6b4d494f0bb3454833e4710c2f~tplv-k3u1fbpfcp-watermark.image?",
            "https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ddbaeabbdf4a464eae2319c3c12ef014~tplv-k3u1fbpfcp-no-mark:800:0:0:0.awebp?",
            "https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8e61331b172a4d75af7363a993c2fdd9~tplv-k3u1fbpfcp-no-mark:800:0:0:0.awebp?",
            "https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1e8e1c080b0e4f41a539ea358aff1ab6~tplv-k3u1fbpfcp-no-mark:800:0:0:0.awebp?",
            "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0ffdf4410e17484a8a97ba47b720f21e~tplv-k3u1fbpfcp-zoom-mark-crop-v2:240:240:0:0.awebp?",
            "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/811f50072d0f4c6eb38218721361b54e~tplv-k3u1fbpfcp-zoom-mark-crop-v2:460:460:0:0.awebp?",
            "https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8566d324f92e476bb2e07f3c6fe6424c~tplv-k3u1fbpfcp-no-mark:800:0:0:0.awebp?",
            "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0284e75d61964b228ac8a5e56c857a40~tplv-k3u1fbpfcp-zoom-mark-crop-v2:460:460:0:0.awebp?"
        ),
    )
    private val iconList = arrayListOf(
        R.drawable.icon_community_tab1,
        R.drawable.icon_errorload,
        R.drawable.icon_errorload
    )
    private val nameList = arrayListOf("张三","BugMaker","小助手","李四")
    private val contentList = arrayListOf<String>(
        "",
        "life is fucking movie",
        "#我的宝藏书籍# 我推荐你读《百年孤独》,《城市发展史》,《美国大城市的死与生》。",
        "老家带的干豇豆快要吃完了，~(>_<)~",
    )


    private var isHide = false
    fun hideCommunityBottomBar(hide:Boolean){
        if (isHide != hide) {
            isHide = hide
            LiveDataBus.with<Boolean>(ConstantEvent.HIDE_COMUNITY_BOTTOM_BAR).postData(hide)
        }
    }

}