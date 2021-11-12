package com.example.baselibrary.utils.view

import android.app.Service
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


fun View.showKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)?.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.GONE
}


inline val View.rootWindowInsetsCompat: WindowInsetsCompat? get() = ViewCompat.getRootWindowInsets(this)

inline val View.windowInsetsControllerCompat: WindowInsetsControllerCompat? get() = ViewCompat.getWindowInsetsController(this)

inline fun View.doOnApplyWindowInsets(noinline action: (View, WindowInsetsCompat) -> WindowInsetsCompat) = ViewCompat.setOnApplyWindowInsetsListener(this, action)


fun <T> viewTags(key: Int) = object : ReadWriteProperty<View, T?> {
    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: View, property: KProperty<*>) =
        thisRef.getTag(key) as T?

    override fun setValue(thisRef: View, property: KProperty<*>, value: T?) =
        thisRef.setTag(key, value)
}
