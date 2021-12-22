package com.xlu.module_tab1.swipe

import android.content.Intent
import android.os.Bundle
import com.example.baselibrary.base_swipeback.SwipeBackActivity
import com.example.baselibrary.lifecycle.ActivityStack
import com.xlu.module_tab1.R

class SwipeBackTestActivity : SwipeBackActivity(R.layout.activity_swipe_back_test) {

    companion object{
        fun openSwipeTestActivity(){
            ActivityStack.currentActivity?.apply {
                val intent = Intent(this,SwipeBackTestActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override suspend fun initData(savedInstanceState: Bundle?) {

    }
}