package com.example.baselibrary.bus

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @ClassName SingleLiveData
 * @Description 单事件响应的liveData
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/5/12 11:08
 */
class SingleLiveData<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        if (hasActiveObservers()) {
            Log.w(TAG, "多个观察者存在的时候，只会有一个被通知到数据更新")
        }

        //比较AtomicBoolean和expect的值，如果一致，执行方法内的语句。其实就是一个if语句
        //把AtomicBoolean的值设成update

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

    companion object {
        private const val TAG = "SingleLiveData"
    }
}
