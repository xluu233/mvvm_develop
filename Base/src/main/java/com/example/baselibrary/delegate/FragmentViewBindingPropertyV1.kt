package com.example.baselibrary.delegate

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Relection
 * <p>
 * Created by pengxr on 2/5/2021
 */

private const val TAG = "ViewBindingProperty"

public inline fun <reified V : ViewBinding> viewBindingV1() = viewBindingV1(V::class.java)

public inline fun <reified T : ViewBinding> viewBindingV1(clazz: Class<T>): FragmentViewBindingPropertyV1<Fragment, T> {
    val bindMethod = clazz.getMethod("bind", View::class.java)
    return FragmentViewBindingPropertyV1 {
        bindMethod(null, it.requireView()) as T
    }
}

/**
 * @param viewBinder 创建绑定类对象
 */
class FragmentViewBindingPropertyV1<in F : Fragment, out V : ViewBinding>(
    private val viewBinder: (F) -> V
) : ReadOnlyProperty<F, V> {

    private var viewBinding: V? = null

    @MainThread
    override fun getValue(thisRef: F, property: KProperty<*>): V {
        // 已经绑定，直接返回
        viewBinding?.let { return it }

        // Use viewLifecycleOwner.lifecycle other than lifecycle
        val lifecycle = thisRef.viewLifecycleOwner.lifecycle
        val viewBinding = viewBinder(thisRef)
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            Log.w(
                TAG, "Access to viewBinding after Lifecycle is destroyed or hasn't created yet. " +
                        "The instance of viewBinding will be not cached."
            )
            // We can access to ViewBinding after Fragment.onDestroyView(), but don't save it to prevent memory leak
        } else {
            lifecycle.addObserver(ClearOnDestroyLifecycleObserver())
            this.viewBinding = viewBinding
        }
        return viewBinding
    }

    @MainThread
    fun clear() {
        viewBinding = null
    }

    private inner class ClearOnDestroyLifecycleObserver : LifecycleObserver {

        private val mainHandler = Handler(Looper.getMainLooper())

        @MainThread
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy(owner: LifecycleOwner) {
            owner.lifecycle.removeObserver(this)
            mainHandler.post { clear() }
        }
    }
}