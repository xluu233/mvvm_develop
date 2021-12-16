package com.example.baselibrary.utils.view

import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.baselibrary.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


const val CLICK_DELAY_TIME = 400L

/*------------------屏蔽View重复点击事件：方法一（推荐）------------------*/
fun <T : View> T.click(delay: Long = CLICK_DELAY_TIME,scope: CoroutineScope ?= null, block: (T) -> Unit) {
    triggerDelay = delay
    setOnClickListener {
        if (clickEnable()) {
            block(this)
        }
    }
}


//给view添加一个上次触发时间的属性（用来屏蔽连击操作）
private var <T : View>T.triggerLastTime: Long
    get() = if (getTag(R.id.triggerLastTimeKey) != null) getTag(R.id.triggerLastTimeKey) as Long else 0
    set(value) {
        setTag(R.id.triggerLastTimeKey, value)
    }

//给view添加一个延迟的属性（用来屏蔽连击操作）
private var <T : View> T.triggerDelay: Long
    get() = if (getTag(R.id.triggerDelayKey) != null) getTag(R.id.triggerDelayKey) as Long else -1
    set(value) {
        setTag(R.id.triggerDelayKey, value)
    }

//判断时间是否满足再次点击的要求（控制点击）
private fun <T : View> T.clickEnable(): Boolean {
    var clickable = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime-triggerLastTime >= triggerDelay) {
        clickable = true
    }
    triggerLastTime = currentClickTime
    return clickable
}



/*------------------屏蔽View重复点击事件：方法二（不推荐）------------------*/
/*------因为这种方法使用全局变量控制，只能同时判断一个view对象，会存在误差------*/

private var lastTime = 0L

fun View.clickNoRepeat(delay: Long = CLICK_DELAY_TIME, onClick: (View) -> Unit) {
    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (lastTime != 0L && (currentTime - lastTime < delay)) {
            return@setOnClickListener
        }
        lastTime = currentTime
        onClick(it)
    }
}

fun setNoRepeatClick(vararg views: View, delay: Long = CLICK_DELAY_TIME, onClick: (View) -> Unit) {
    views.forEach {
        it.clickNoRepeat(delay = delay) { view ->
            onClick.invoke(view)
        }
    }
}

