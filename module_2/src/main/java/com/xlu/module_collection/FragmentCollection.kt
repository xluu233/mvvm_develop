package com.xlu.module_collection

import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.example.baselibrary.base.BaseFragment
import com.example.baselibrary.viewbinding.viewBinding
import com.example.baselibrary.utils.view.click
import com.example.xlulibrary.ToastBox
import com.xlu.common.bean.RouterBeanSerializable
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantParams
import com.xlu.common.server.ServerUtil.getLoginServer
import com.xlu.module_collection.databinding.FragmentCollectionBinding
import android.content.Intent
import android.net.Uri

import java.lang.StringBuilder

import android.content.pm.PackageManager
import android.content.ComponentName

import android.content.pm.ResolveInfo
import java.util.*


class FragmentCollection : BaseFragment(R.layout.fragment_collection) {

    companion object {
        @JvmStatic
        fun newInstance() = FragmentCollection()
    }

    private val binding by viewBinding(FragmentCollectionBinding::bind)
    private val viewModel:CollectionViewModel by activityViewModels()

    override suspend fun initData() {
        initClick()
    }


    private fun initClick() {
        binding.testArouter.click {
            //跳转到Login模块 -> LoginActivity
            ARouter.getInstance().build(ConstantARouter.LoginActivity).navigation()
        }

        binding.testArouterParams.click {
            //携带参数跳转
            ARouter.getInstance().build(ConstantARouter.LoginActivity)
                .withLong(ConstantParams.key1, 666L)
                .withString(ConstantParams.key2, "888")
                .withSerializable(ConstantParams.key3,
                    RouterBeanSerializable(id = 123,name = "哈哈哈哈")
                )
                .navigation()
        }

        binding.testProvider.click {
            val token:String = getLoginServer().getToken()
            ToastBox.showToast(token)
        }

        binding.testFlow.click {

        }
        binding.testMusic.click {
            //网易云音乐，447925058曲谱id，加上/?autoplay=1自动播放
//            goUrlScheme("orpheus://song/447925058")

            //自定义
//            goUrlScheme("app://bugmaker:8888/launch")

            //酷狗
//            goUrlScheme("KugouKtvUrl://")
            //openApp()

            //QQ音乐
            //{"radioId":"199","action":"play","cache":"1"}
            goUrlScheme("qqmusic://qq.com/media/playRadio?p=%7B%22radioId%22%3A%22199%22%2C%22action%22%3A%22play%22%2C%22cache%22%3A%221%22%7D")

            //Apple music
//            goUrlScheme("https://geo.itunes.apple.com/au/album/monsieur-cousteau/id496076893?i=496076903&app=itunes")


        }

    }

    /**
     * TODO 利用UrlScheme协议跳转
     * @param url
     */
    private fun goUrlScheme(url:String){
        val packageManager: PackageManager = requireActivity().packageManager
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        val activities = packageManager.queryIntentActivities(intent, 0)
        if (activities.isEmpty()) {
            ToastBox.showToast("null")
        }else{
            startActivity(intent)
        }
    }

    /**
     * 检查已安装的应用程序。并打开
     */
    private fun openApp() {
        //应用过滤条件
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val mPackageManager = mContext.packageManager
        val mAllApps = mPackageManager.queryIntentActivities(mainIntent, 0)
        //按包名排序
        Collections.sort(mAllApps, ResolveInfo.DisplayNameComparator(mPackageManager))
        //遍历
        for (res in mAllApps) {
            //该应用的包名和主Activity
            val pkg = res.activityInfo.packageName
            val cls = res.activityInfo.name

            // 打开酷狗音乐APP
            if (pkg.contains("com.kugou.android")) {
                val componet = ComponentName(pkg, cls)
                val intent = Intent()
                intent.component = componet
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext.startActivity(intent)
            }
        }
    }


}