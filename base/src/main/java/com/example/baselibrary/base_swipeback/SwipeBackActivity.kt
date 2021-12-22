package com.example.baselibrary.base_swipeback

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.base.BaseActivity

abstract class SwipeBackActivity(contentLayoutId: Int) : BaseActivity(contentLayoutId) {

    lateinit var swipeBackLayout: SwipeBackLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        swipeBackLayout = SwipeBackLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            edgeLevel = SwipeBackLayout.EdgeLevel.MAX
//            edgeLevelPixels = widthPixel
//            preDragPercent = percent
//            currentSwipeOrientation = orientation
//            setEnableGesture(enable)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        swipeBackLayout.attachToActivity(this)
    }

    override fun <T : View?> findViewById(id: Int): T {
        return super.findViewById<T>(id) ?: swipeBackLayout.findViewById<T>(id)
    }


    /**
     * 嵌套fragment的情况判断
     */
    fun swipeBackPriority(): Boolean {
        return supportFragmentManager.backStackEntryCount <= 1
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
            return
        }
        super.onBackPressed()
    }

}
