package com.example.baselibrary.base_swipeback

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.baselibrary.R
import com.example.baselibrary.base.BaseActivity

open class SwipeBackActivity(contentLayoutId: Int) : AppCompatActivity(contentLayoutId) {

    lateinit var swipeBackLayout: SwipeBackLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.decorView.background = null
        swipeBackLayout = SwipeBackLayout(this)
        swipeBackLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        setEdgeLevel(SwipeBackLayout.EdgeLevel.MAX)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        swipeBackLayout.attachToActivity(this)
    }

    override fun <T : View?> findViewById(id: Int): T {
        return super.findViewById<T>(id) ?: swipeBackLayout.findViewById<T>(id)
    }

    fun setEdgeLevel(edgeLevel: SwipeBackLayout.EdgeLevel) {
        swipeBackLayout.edgeLevel = edgeLevel
    }

    protected fun setEdgeLevel(widthPixel: Int) {
        swipeBackLayout.edgeLevelPixels = widthPixel
    }

    protected fun setPreDragPercent(percent: Float) {
        swipeBackLayout.preDragPercent = percent
    }

    protected fun setEdgeOrientation(orientation: Int) {
        swipeBackLayout.currentSwipeOrientation = orientation
    }

    fun setSwipeBackEnable(enable: Boolean) {
        swipeBackLayout.setEnableGesture(enable)
    }

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
