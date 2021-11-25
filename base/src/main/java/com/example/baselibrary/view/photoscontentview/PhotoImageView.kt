package com.example.baselibrary.view.photoscontentview

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView

class PhotoImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val drawable = drawable
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    drawable?.mutate()?.colorFilter = BlendModeColorFilter(Color.GRAY, BlendMode.MULTIPLY)
                } else {
                    drawable?.mutate()?.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
                }
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                val drawableUp = drawable
                drawableUp?.mutate()?.clearColorFilter()
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        setImageDrawable(null)
    }
}