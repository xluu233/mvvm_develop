package com.xlu.module_tab1.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.baselibrary.utils.log.xLog
import com.example.baselibrary.utils.other.screenHeight
import com.example.baselibrary.utils.other.screenWidth
import com.example.baselibrary.utils.view.dp
import com.xlu.module_tab1.R

/**
 * @ClassName AdjuestDistanceView
 * @Description TODO 动态调节距离View
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/12/22 14:39
 */
class AdjustDistanceView : ViewGroup {

    private val TAG = "AdjustDistanceView"
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private val paint_line by lazy {
        Paint()
    }

    private val paint_fill by lazy {
        Paint()
    }

    private val dragView by lazy {
        getIconView()
    }

    private fun init(){
        paint_line.color = resources.getColor(R.color.red)
        paint_line.strokeWidth = 20f
        paint_line.style = Paint.Style.STROKE

        paint_fill.apply {
            style = Paint.Style.FILL
            color = resources.getColor(R.color.red)
            alpha = ((0.3)*255).toInt()
        }
    }

    companion object{

        //下边界距离，默认屏幕的三分之一
        var bottomDistance = screenHeight/3

        var minHeight = screenHeight/5

        val iconSize = 60.dp

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return super.onTouchEvent(event)
        var handle = true
        val y = event.y.toInt()
        val x = event.x.toInt()

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                bottomDistance = event.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                bottomDistance = event.y.toInt()
            }
            MotionEvent.ACTION_UP -> {
                bottomDistance = event.y.toInt()
            }
        }
        if (y < minHeight){
            handle = false
            bottomDistance = minHeight
        }else{
            bottomDistance = y
        }
        if (y > reallyHeight){
            handle = false
            bottomDistance = reallyHeight
        }else{
            bottomDistance = y
        }
        if (handle){
            invalidate()
        }
        return handle || super.onTouchEvent(event)
    }

    private var reallyWidth = 0
    private var reallyHeight = 0

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        reallyHeight = b-t
        reallyWidth = r-l

        val icon_half = (iconSize/2).toInt()
        val left = reallyWidth/2 - icon_half
        val right = reallyWidth/2 + icon_half
        val top = bottomDistance - icon_half
        val bottom = bottomDistance + icon_half
        dragView.layout(left, top, right, bottom)

        addView(dragView)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        xLog.d(TAG,"${bottomDistance}")
        val rect = Rect(0, 0, width, bottomDistance)
        canvas?.drawRect(rect,paint_fill)
        canvas?.drawRect(rect,paint_line)

//        val rectF = RectF(0F, 0F, width.toFloat(), bottomDistance.toFloat())
//        canvas?.drawRect(rectF,paint_fill)
//        canvas?.drawRoundRect(rectF,30f,30f,paint_line)

        dragView.apply {
            x = reallyWidth/2 - 30.dp
            y = bottomDistance - 30.dp
        }
    }


    private fun getIconView(): ImageView {
        val imageView = ImageView(context)
        imageView.layoutParams = ViewGroup.LayoutParams(60.dp.toInt(),60.dp.toInt())
        imageView.apply {
            setImageResource(R.drawable.icon_drag)
            x = reallyWidth/2 - 30.dp
            y = bottomDistance - 30.dp
        }
        return imageView
    }
}