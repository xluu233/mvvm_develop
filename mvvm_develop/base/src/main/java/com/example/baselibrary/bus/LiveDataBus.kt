package com.example.baselibrary.bus

import androidx.lifecycle.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList


/**
 * @ClassName LiveDataBus
 * @Description 参考：https://github.com/fmtjava/LiveDataBus
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/8/17 10:38
 */
object LiveDataBus {

    //一个事件对应一个LiveData
    private val eventMap = ConcurrentHashMap<String, StickLiveData<*>>()

    //一个事件对应多个页面
    private val lifecycleMap = ConcurrentHashMap<String, CopyOnWriteArrayList<LifecycleOwner>>()

    //一个页面对应多个事件
    private val eventTypeMap = ConcurrentHashMap<LifecycleOwner, CopyOnWriteArrayList<String>>()

    //注册对应事件
    fun <T> with(eventName: String): StickLiveData<T> {
        var liveData = eventMap[eventName]

        if (liveData == null) {
            liveData = StickLiveData<T>(eventName)
            eventMap[eventName] = liveData
        }
        return liveData as StickLiveData<T>
    }

    //获取当前事件注册的页面集合
    private fun getLifecycleOwners(eventName: String): CopyOnWriteArrayList<LifecycleOwner> {
        var lifecycleOwners = lifecycleMap[eventName]
        if (lifecycleOwners == null) {
            lifecycleOwners = CopyOnWriteArrayList<LifecycleOwner>()
            lifecycleMap[eventName] = lifecycleOwners
        }
        return lifecycleOwners
    }

    //保存页面事件
    private fun addLifecycleOwner(eventName: String, lifecycleOwner: LifecycleOwner) {
        val lifecycleOwners = getLifecycleOwners(eventName)
        if (!lifecycleOwners.contains(lifecycleOwner)) {
            lifecycleOwners.add(lifecycleOwner)
        }
    }

    //获取当前页面注册的哪些事件
    private fun getEventTypeList(lifecycleOwner: LifecycleOwner): CopyOnWriteArrayList<String> {
        var eventTypeList = eventTypeMap[lifecycleOwner]
        if (eventTypeList == null) {
            eventTypeList = CopyOnWriteArrayList<String>()
            eventTypeMap[lifecycleOwner] = eventTypeList
        }
        return eventTypeList
    }

    private fun addEventType(eventName: String, lifecycleOwner: LifecycleOwner) {
        val eventTypeList = getEventTypeList(lifecycleOwner)
        if (!eventTypeList.contains(eventName)) {
            eventTypeList.add(eventName)
        }
    }

    /**
     * 自定义粘性LiveData
     */
    class StickLiveData<T>(private val eventName: String) : LiveData<T>() {

        internal var mStickData: T? = null//粘性事件对应的数据
        internal var mVersion = 0//记录事件发送的次数

        //支持主线程中发送数据
        fun setData(data: T) {
            setValue(data)
        }

        //支持主线程、子线程发送数据
        fun postData(data: T) {
            postValue(data)
        }

        //支持主线程中发送粘性数据
        fun setStickData(data: T) {
            mStickData = data
            setValue(data)
        }

        //支持主线程、子线程中发送粘性数据
        fun postStickData(data: T) {
            mStickData = data
            postValue(data)
        }

        //发送数据最终都会走到这里
        override fun setValue(value: T) {
            //记录发送的次数
            mVersion++
            super.setValue(value)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            observeStick(owner, observer, false)
        }

        //stick --> 用于区分是否接受粘性事件
        fun observeStick(owner: LifecycleOwner, observer: Observer<in T>, stick: Boolean = true) {
            super.observe(owner, StickWarpObserver(this, observer, stick))
            //添加页面事件
            addLifecycleOwner(eventName, owner)
            //监听页面的生命周期
            if (!eventTypeMap.containsKey(owner)) {
                owner.lifecycle.addObserver(LifecycleEventObserver { source, event ->
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        //1.移除当前页面注册的事件
                        val lifecycleOwners = getLifecycleOwners(eventName)
                        lifecycleOwners.remove(source)

                        //2.当前页面销毁时，判断其它未销毁的页面仍注册着该事件
                        //2.1 若没有其它页面注册，则移除
                        val eventTypeList = getEventTypeList(owner)

                        eventTypeList.forEach { eventType ->
                            if (getLifecycleOwners(eventType).isEmpty()) {
                                eventMap.remove(eventType)
                                lifecycleMap.remove(eventType)
                            }
                        }
                        eventTypeMap.remove(owner)
                    }
                })
            }
            addEventType(eventName, owner)
        }
    }

    //装饰者模式对原先的Observer进行包装
    internal class StickWarpObserver<T>(
        private val stickLiveData: StickLiveData<T>,
        private val observer: Observer<in T>,
        private val stick: Boolean
    ) : Observer<T> {

        //创建Observer时默认赋值为LiveData的Version,防止黏性事件
        private var mLastVersion = stickLiveData.mVersion

        override fun onChanged(t: T) {
            /**
             * 在这里控制是否接受粘否，StickWarpObserver本质上没有改变原先的粘性事件发送，
             * 只是在onChanged回调时，通过stick判断是否执行被装饰的Observer的onChanged方法
             */
            if (mLastVersion >= stickLiveData.mVersion) {
                if (stick && stickLiveData.mStickData != null) {
                    observer.onChanged(t)
                }
                return
            }
            mLastVersion = stickLiveData.mVersion
            observer.onChanged(t)
        }
    }
}