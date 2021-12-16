package com.example.mvvm_develop.activity

import android.annotation.TargetApi
import android.app.SharedElementCallback
import android.content.Intent
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.drake.statusbar.darkMode
import com.drake.statusbar.immersive
import com.drake.statusbar.statusBarColor
import com.example.baselibrary.base.BaseActivity
import com.example.baselibrary.lifecycle.ActivityStack
import com.example.baselibrary.utils.log.xLog
import com.example.baselibrary.utils.other.Color
import com.example.baselibrary.viewbinding.findRootView
import com.example.baselibrary.viewbinding.viewBinding
import com.example.module_community.COIL_POSITION
import com.example.mvvm_develop.R
import com.example.mvvm_develop.adapter.ImagePreviewAdapter
import com.example.mvvm_develop.databinding.FragmentImagePreviewBinding
import com.xlu.common.constants.ConstantARouter
import com.xlu.common.constants.ConstantParams

@Route(path = ConstantARouter.ImagePreviewActivity)
class ImagePreviewActivity : BaseActivity(R.layout.fragment_image_preview) {


    @Autowired(name = ConstantParams.PreviewImageList)
    @JvmField
    var list: ArrayList<String>  = arrayListOf()

    @Autowired(name = ConstantParams.PreviewImagePosition)
    @JvmField
    var idx: Int = 0


    private var currentUrl = ""

    private var isShareElement = false

    private val binding by viewBinding(FragmentImagePreviewBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()

        /*val transName = list[idx]
        ViewCompat.setTransitionName(binding.previewVp.findViewWithTag(), transName)*/
    }


    override suspend fun initData(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        xLog.d(TAG,"list:${list.size},index:$idx")

        initView()
        initPage()
        setEnterShareCallback()
    }

    private fun initView() {
        this.immersive(darkMode = true)

    }

    private fun initPage() {
        currentUrl = list[idx]

        binding.previewTitle.text = "$idx/${list.size}"
        binding.previewVp.apply {
            adapter = ImagePreviewAdapter(list)
            offscreenPageLimit = 5
            setCurrentItem(idx,false)

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    Log.d(TAG, "onPageSelected: $position")
                    idx = position
                    binding.previewTitle.text = "${idx+1}/${list.size}"
                    currentUrl = list[position]
                }
            })

            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    this@ImagePreviewActivity.supportStartPostponedEnterTransition()
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })

        }

    }

    @TargetApi(22)
    override fun supportFinishAfterTransition() {
        xLog.d(TAG,"supportFinishAfterTransition")
        val data = Intent()
        data.putExtra(COIL_POSITION,binding.previewVp.currentItem)
        setResult(RESULT_OK, data)
        xLog.d(TAG,"position:${data.getIntExtra(COIL_POSITION,-1)}")
        super.supportFinishAfterTransition()
    }


    override fun onBackPressed() {
        xLog.d(TAG,"onBackPressed")
        val data = Intent()
        data.putExtra(COIL_POSITION,binding.previewVp.currentItem)
        setResult(RESULT_OK, data)
        xLog.d(TAG,"position:${data.getIntExtra(COIL_POSITION,-1)}")
        super.supportFinishAfterTransition()
    }

    private fun setEnterShareCallback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置共享元素们的回调
            /*this.setEnterSharedElementCallback(object : SharedElementCallback() {
                override fun onMapSharedElements(names: MutableList<String?>, sharedElements: MutableMap<String?, View?>) {
                    //names.clear()
                    names.add(currentUrl)
                    //sharedElements.clear()
                    sharedElements[currentUrl] = binding.previewVp.findViewWithTag<View>(currentUrl)

                    names.forEach {
                        Log.d(TAG, "onMapSharedElements-1: $it")
                    }
                    sharedElements.forEach { t, u ->
                        Log.d(TAG, "onMapSharedElements-2: $t,$u")
                    }
                }

            })*/

            //这个可以看做个管道  每次进入和退出的时候都会进行调用  进入的时候获取到前面传来的共享元素的信息
            //退出的时候 把这些信息传递给前面的activity
            //同时向sharedElements里面put view,跟对view添加transitionname作用一样

            this.setEnterSharedElementCallback(object : SharedElementCallback() {
                override fun onMapSharedElements(names: MutableList<String?>, sharedElements: MutableMap<String?, View?>) {
                    sharedElements.clear()
                    val tag = currentUrl
                    val position = binding.previewVp.currentItem
                    val view = binding.previewVp.findViewWithTag<View>(tag)
                    xLog.d(TAG,"tag:$tag,  position:$position,  view:$view")
                    sharedElements.set(tag,view)
                }

            })
        }

    }


}