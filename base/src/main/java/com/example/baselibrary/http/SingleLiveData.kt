package com.example.baselibrary.http

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.baselibrary.utils.log.xLog
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @ClassName SingleLiveData
 * @Description  单事件响应的liveData
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/12 11:08
 */
class SingleLiveData<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        if (hasActiveObservers()) {
            xLog.w(TAG, "多个观察者存在的时候，只会有一个被通知到数据更新")
        }

        super.observe(owner, Observer { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    override fun setValue(value: T?) {
        mPending.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private const val TAG = "SingleLiveData"
    }
}
