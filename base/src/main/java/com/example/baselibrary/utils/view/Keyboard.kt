package com.example.baselibrary.utils.view

import android.widget.EditText
import androidx.core.view.WindowInsetsCompat.Type


inline fun EditText.showKeyboard() = windowInsetsControllerCompat?.show(Type.ime())

inline fun EditText.hideKeyboard() = windowInsetsControllerCompat?.hide(Type.ime())

inline fun EditText.toggleKeyboard() = if (isKeyboardVisible) hideKeyboard() else showKeyboard()

inline val EditText.isKeyboardVisible: Boolean get() = rootWindowInsetsCompat?.isVisible(Type.ime()) == true

inline val EditText.keyboardHeight: Int get() = rootWindowInsetsCompat?.getInsets(Type.ime())?.bottom ?: -1
