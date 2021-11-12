package com.example.baselibrary.utils.view

import android.content.res.Resources
import android.util.TypedValue


/*------dp转换--------*/
inline val Int.dp: Float get() = toFloat().dp

inline val Long.dp: Float get() = toFloat().dp

inline val Double.dp: Float get() = toFloat().dp

inline val Float.dp: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)



/*------sp转换--------*/
inline val Int.sp: Float get() = toFloat().sp

inline val Long.sp: Float get() = toFloat().sp

inline val Double.sp: Float get() = toFloat().sp

inline val Float.sp: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics)



/*------px-dp转换--------*/
inline fun Int.pxToDp(): Int = toFloat().pxToDp()

inline fun Long.pxToDp(): Int = toFloat().pxToDp()

inline fun Double.pxToDp(): Int = toFloat().pxToDp()

inline fun Float.pxToDp(): Int = (this / Resources.getSystem().displayMetrics.density + 0.5f).toInt()



/*------px-sp转换--------*/
inline fun Int.pxToSp(): Int = toFloat().pxToSp()

inline fun Long.pxToSp(): Int = toFloat().pxToSp()

inline fun Double.pxToSp(): Int = toFloat().pxToSp()

inline fun Float.pxToSp(): Int = (this / Resources.getSystem().displayMetrics.scaledDensity + 0.5f).toInt()
