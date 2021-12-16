package com.example.baselibrary.utils.activity

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.baselibrary.lifecycle.ActivityStack
import com.example.xlulibrary.ToastBox


inline fun ComponentActivity.pressBackTwiceToExitApp(toastText: String, delayMillis: Long = 2000) =
  pressBackTwiceToExitApp(delayMillis) { ToastBox.showToast(toastText) }

inline fun ComponentActivity.pressBackTwiceToExitApp(delayMillis: Long = 2000, crossinline onFirstBackPressed: () -> Unit) {
  onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
      private var lastBackTime: Long = 0

      override fun handleOnBackPressed() {
          val currentTime = System.currentTimeMillis()
          if (currentTime - lastBackTime > delayMillis) {
            onFirstBackPressed()
            lastBackTime = currentTime
          } else {
            ActivityStack.finishAllActivity()
          }
      }
  })
}

inline fun ComponentActivity.pressBackToNotExitApp() {
  onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
    override fun handleOnBackPressed() {
      moveTaskToBack(false)
    }
  })
}


inline val Activity.contentView: View
  get() = (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)

val Context.activity: Activity?
  get() {
    var context: Context? = this
    while (context is ContextWrapper) {
      if (context is Activity) {
        return context
      }
      context = context.baseContext
    }
    return null
  }

inline val Context.context: Context get() = this

inline val Activity.activity: Activity get() = this

inline val FragmentActivity.fragmentActivity: FragmentActivity get() = this

inline val ComponentActivity.lifecycleOwner: LifecycleOwner get() = this
