package com.example.baselibrary.base_swipeback

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.baselibrary.R
import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


//
// Created by Ivan200 on 16.09.2019.
//

open class SwipeBackLayout : FrameLayout {
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            : super(context, attrs, defStyleAttr) {
        init()
    }

    private var mScrollFinishThreshold: Float = DEFAULT_SCROLL_THRESHOLD

    private var mHelper: ViewDragHelper? = null

    private var mScrollPercent: Float = 0f
    private var mScrimOpacity: Float = 0f

    private var mActivity: FragmentActivity? = null
    private var mContentView: View? = null

    private var mShadowLeft: Drawable? = null
    private var mShadowRight: Drawable? = null
    private var mShadowTop: Drawable? = null
    private var mShadowBottom: Drawable? = null
    private val mTmpRect: Rect = Rect()

    private var mEnable = true

    /**
     * Enable edge tracking for the selected edges of the parent view.
     * The callback's [ViewDragHelper.Callback.onEdgeTouched] and
     * [ViewDragHelper.Callback.onEdgeDragStarted] methods will only be invoked
     * for edges for which edge tracking has been enabled.
     */
    var currentSwipeOrientation: Int = ViewDragHelper.EDGE_LEFT
        set(it) {
            field = it
            mHelper?.setEdgeTrackingEnabled(it)
            setShadow(it)
            validateEdgeLevel(edgeLevelPixels, edgeLevel)
        }

    val mEdgeFlag get() = currentSwipeOrientation

    val canSwipeFromLeft    get() = mEdgeFlag and ViewDragHelper.EDGE_LEFT != 0
    val canSwipeFromTop     get() = mEdgeFlag and ViewDragHelper.EDGE_TOP != 0
    val canSwipeFromRight   get() = mEdgeFlag and ViewDragHelper.EDGE_RIGHT != 0
    val canSwipeFromBottom  get() = mEdgeFlag and ViewDragHelper.EDGE_BOTTOM != 0

    var edgeLevel: EdgeLevel? = null
        set(edgeLevel) {
            field = edgeLevel
            validateEdgeLevel(0, edgeLevel)
        }

    var edgeLevelPixels: Int = 0
        set(edgeLevelPixels) {
            field = edgeLevelPixels
            validateEdgeLevel(edgeLevelPixels, null)
        }

    var preDragPercent: Float = 8f
        set(preDragPercent) {
            field = preDragPercent
            preCounter = if (preDragPercent > 0f) DragPreCounter() else null
        }

    private var preCounter: DragPreCounter? = DragPreCounter()

    /**
     * The set of listeners to be sent events through.
     */
    private var mListeners: MutableList<OnSwipeListener>? = null

    enum class EdgeLevel {
        MAX, MIN, MED
    }

    private fun init() {
        mHelper = ViewDragHelper.create(this, ViewDragCallback())

        currentSwipeOrientation = ViewDragHelper.EDGE_LEFT
    }

    /**
     * Set scroll threshold, we will close the activity, when scrollPercent over
     * this value
     *
     * @param threshold
     */
    fun setScrollThresHold(threshold: Float) {
        require(!(threshold >= 1.0f || threshold <= 0)) { "Threshold value should be between 0 and 1.0" }
        mScrollFinishThreshold = threshold
    }


//    enum class SwipeOrientation private constructor(var edges: Int) {
//        LEFT(ViewDragHelper.EDGE_LEFT),
//        RIGHT(ViewDragHelper.EDGE_RIGHT),
//        HORISONTAL(ViewDragHelper.EDGE_LEFT or ViewDragHelper.EDGE_RIGHT),
//        TOP(ViewDragHelper.EDGE_TOP),
//        BOTTOM(ViewDragHelper.EDGE_BOTTOM),
//        VERTICAL(ViewDragHelper.EDGE_TOP or ViewDragHelper.EDGE_BOTTOM),
//        ALL(ViewDragHelper.EDGE_ALL);
//    }

    fun setShadow(orientation: Int) {
        if (mShadowLeft == null && orientation and ViewDragHelper.EDGE_LEFT != 0)
            mShadowLeft = ContextCompat.getDrawable(context, R.drawable.shadow_left)!!
        if (mShadowRight == null && orientation and ViewDragHelper.EDGE_RIGHT != 0)
            mShadowRight = ContextCompat.getDrawable(context, R.drawable.shadow_right)!!
        if (mShadowTop == null && orientation and ViewDragHelper.EDGE_TOP != 0)
            mShadowTop = ContextCompat.getDrawable(context, R.drawable.shadow_top)!!
        if (mShadowBottom == null && orientation and ViewDragHelper.EDGE_BOTTOM != 0)
            mShadowBottom = ContextCompat.getDrawable(context, R.drawable.shadow_bottom)!!
    }

    private fun validateEdgeLevel(widthPixel: Int, edgeLevel: EdgeLevel?) {
        try {
            val metrics = DisplayMetrics()
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowManager.defaultDisplay.getMetrics(metrics)
            val mEdgeSize = mHelper?.javaClass?.getDeclaredField("mEdgeSize") ?: return
            mEdgeSize.isAccessible = true
            if (widthPixel != 0) {
                mEdgeSize.setInt(mHelper, widthPixel)
            } else {
                var size = metrics.widthPixels
                if(canSwipeFromTop || canSwipeFromBottom){
                    size = metrics.heightPixels
                }
                when (edgeLevel) {
                    EdgeLevel.MAX -> mEdgeSize.setInt(mHelper, size)
                    EdgeLevel.MED -> mEdgeSize.setInt(mHelper, size / 2)
                    EdgeLevel.MIN -> mEdgeSize.setInt(mHelper, (20 * metrics.density + 0.5f).toInt())
                }
            }
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }

    /**
     * Add a callback to be invoked when a swipe event is sent to this view.
     *
     * @param listener the swipe listener to attach to this view
     */
    fun addSwipeListener(listener: OnSwipeListener) {
        if (mListeners == null) {
            mListeners = ArrayList()
        }
        mListeners?.add(listener)
    }

    /**
     * Removes a listener from the set of listeners
     *
     * @param listener
     */
    fun removeSwipeListener(listener: OnSwipeListener) {
        if (mListeners == null) {
            return
        }
        mListeners?.remove(listener)
    }

    interface OnSwipeListener {
        /**
         * Invoke when state change
         *
         * @param state flag to describe scroll state
         * @see .STATE_IDLE
         *
         * @see .STATE_DRAGGING
         *
         * @see .STATE_SETTLING
         */
        fun onDragStateChange(state: Int)

        /**
         * Invoke when edge touched
         *
         * @param oritentationEdgeFlag edge flag describing the edge being touched
         * @see .EDGE_LEFT
         *
         * @see .EDGE_RIGHT
         */
        fun onEdgeTouch(oritentationEdgeFlag: Int)

        /**
         * Invoke when scroll percent over the threshold for the first time
         *
         * @param scrollPercent scroll percent of this view
         */
        fun onDragScrolled(scrollPercent: Float)
    }

    override fun drawChild(canvas: Canvas, child: View, drawingTime: Long): Boolean {
        val isDrawView = child === mContentView
        val drawChild = super.drawChild(canvas, child, drawingTime)
        if (isDrawView && mScrimOpacity > 0 && mHelper?.viewDragState != ViewDragHelper.STATE_IDLE) {
            drawShadow(canvas, child)
            drawScrim(canvas, child)
        }
        return drawChild
    }

    private fun drawShadow(canvas: Canvas, child: View) {
        val childRect = mTmpRect
        child.getHitRect(childRect)

        if (canSwipeFromLeft)
            checkDrawShadow(canvas, childRect, mShadowLeft, DragDirection.LEFT)
        if (canSwipeFromRight)
            checkDrawShadow(canvas, childRect, mShadowRight, DragDirection.RIGHT)
        if (canSwipeFromTop)
            checkDrawShadow(canvas, childRect, mShadowTop, DragDirection.TOP)
        if (canSwipeFromBottom)
            checkDrawShadow(canvas, childRect, mShadowBottom, DragDirection.BOTTOM)
    }

    private fun checkDrawShadow(
        canvas: Canvas,
        childRect: Rect,
        drawable: Drawable?,
        drawablePos: DragDirection
    ) {
        when (drawablePos) {
            DragDirection.LEFT -> drawable?.setBounds(
                childRect.left - mShadowLeft?.intrinsicWidth!!,
                childRect.top,
                childRect.left,
                childRect.bottom
            )
            DragDirection.RIGHT -> drawable?.setBounds(
                childRect.right,
                childRect.top,
                childRect.right + mShadowRight?.intrinsicWidth!!,
                childRect.bottom
            )
            DragDirection.TOP -> drawable?.setBounds(
                childRect.left,
                childRect.top - mShadowTop?.intrinsicHeight!!,
                childRect.right,
                childRect.top
            )
            DragDirection.BOTTOM -> drawable?.setBounds(
                childRect.left,
                childRect.bottom,
                childRect.right,
                childRect.bottom + mShadowBottom?.intrinsicHeight!!
            )
        }

        drawable?.alpha = (mScrimOpacity * FULL_ALPHA).toInt()
        drawable?.draw(canvas)
    }


    private fun drawScrim(canvas: Canvas, child: View) {
        val baseAlpha = (DEFAULT_SCRIM_COLOR and -0x1000000).ushr(24)
        val alpha = (baseAlpha * mScrimOpacity).toInt()
        val color = alpha shl 24

        val paint = Paint()
        paint.color = color

        if (canSwipeFromLeft) {
            canvas.drawRect(0f, 0f, child.left.toFloat(), height.toFloat(), paint)
        }
        if (canSwipeFromRight) {
            canvas.drawRect(child.right.toFloat(), 0F, right.toFloat(), height.toFloat(), paint)
        }
        if (canSwipeFromTop) {
            canvas.drawRect(0F, 0F, width.toFloat(), child.top.toFloat(), paint)
        }
        if (canSwipeFromBottom) {
            canvas.drawRect(0F, child.bottom.toFloat(), right.toFloat(), height.toFloat(), paint)
        }
    }

    override fun computeScroll() {
        mScrimOpacity = 1 - mScrollPercent
        if (mScrimOpacity >= 0) {
            if (mHelper?.continueSettling(true) == true) {
                ViewCompat.postInvalidateOnAnimation(this)
            }
        }
    }


    fun attachToActivity(activity: FragmentActivity) {
        mActivity = activity
        val a = activity.theme.obtainStyledAttributes(intArrayOf(android.R.attr.windowBackground))
        val background = a.getResourceId(0, 0)
        a.recycle()

        val decor = activity.window.decorView as ViewGroup
        val decorChild = decor.getChildAt(0) as ViewGroup
        decorChild.setBackgroundResource(background)
        decor.removeView(decorChild)
        addView(decorChild)
        setContentView(decorChild)
        decor.addView(this)
    }


    private fun setContentView(view: View) {
        mContentView = view
    }

    fun setEnableGesture(enable: Boolean) {
        mEnable = enable
    }

    internal inner class ViewDragCallback : ViewDragHelper.Callback() {

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            val dragEnable = (edgeLevel == EdgeLevel.MAX || mHelper?.isEdgeTouched(mEdgeFlag, pointerId) == true)
            if (dragEnable) {
                mListeners?.forEach {
                    it.onEdgeTouch(mEdgeFlag)
                }

                if(preCounter?.canCaptureView() == false){
                    return false
                }

            }
            return dragEnable
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            val direction = preCounter?.getDragDirection()
            return if (preCounter == null
                || direction == DragDirection.LEFT
                || direction == DragDirection.RIGHT
            ) {
                when {
                    (canSwipeFromLeft && canSwipeFromRight) -> {
                        min(child.width, max(left, -child.width))
                    }
                    canSwipeFromLeft -> min(child.width, max(left, 0))
                    canSwipeFromRight -> min(0, max(left, -child.width))
                    else -> 0
                }
            } else 0
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            val direction = preCounter?.getDragDirection()
            return if (preCounter == null
                || direction == DragDirection.TOP
                || direction == DragDirection.BOTTOM
            ) {
                when {
                    (canSwipeFromTop && canSwipeFromBottom) -> min(child.height,max(top, -child.height))
                    canSwipeFromTop -> min(child.height, max(top, 0))
                    canSwipeFromBottom -> min(0, max(top, -child.height))
                    else -> 0
                }
            } else 0
        }


        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int
        ) {
            super.onViewPositionChanged(changedView, left, top, dx, dy)
            mScrollPercent = when {
                left > 0 && canSwipeFromLeft -> abs(left.toFloat() / (width + mShadowLeft?.intrinsicWidth!!))
                left < 0 && canSwipeFromRight -> abs(left.toFloat() / (mContentView?.width!! + mShadowRight?.intrinsicWidth!!))
                top > 0 && canSwipeFromTop -> abs(top.toFloat() / (height + mShadowTop?.intrinsicHeight!!))
                top < 0 && canSwipeFromBottom -> abs(top.toFloat() / (mContentView?.height!! + mShadowBottom?.intrinsicHeight!!))
                else -> 0f
            }

            invalidate()

            if (mListeners?.isNotEmpty() == true
                && mHelper?.viewDragState == ViewDragHelper.STATE_DRAGGING
                && mScrollPercent <= 1 && mScrollPercent > 0) {
                mListeners?.forEach {
                    it.onDragScrolled(mScrollPercent)
                }
            }

            if (mScrollPercent > 1) {
                if (mActivity?.isFinishing == false) {
                    mActivity?.finish()
                    mActivity?.overridePendingTransition(0, 0)
                }
            }
        }

        override fun getViewHorizontalDragRange(child: View): Int {
            return if ((canSwipeFromLeft || canSwipeFromRight)
                && ((mActivity != null && (mActivity as SwipeBackActivity).swipeBackPriority()))
            ) 1 else 0
        }

        override fun getViewVerticalDragRange(child: View): Int {
            return if ((canSwipeFromTop || canSwipeFromBottom)
                && ((mActivity != null && (mActivity as SwipeBackActivity).swipeBackPriority()))
            ) 1 else 0
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            val left = when {
                releasedChild.x > 0 && canSwipeFromLeft && (xvel > 0 || (xvel == 0f && mScrollPercent > mScrollFinishThreshold)) -> {
                    releasedChild.width + mShadowLeft?.intrinsicWidth!! + OVERSCROLL_DISTANCE
                }
                releasedChild.x < 0 && canSwipeFromRight && (xvel < 0 || (xvel == 0f && mScrollPercent > mScrollFinishThreshold)) -> {
                    -(releasedChild.width + mShadowRight?.intrinsicWidth!! + OVERSCROLL_DISTANCE)
                }
                else -> 0
            }
            val top = when {
                releasedChild.y > 0 && canSwipeFromTop && (yvel > 0 || (yvel == 0f && mScrollPercent > mScrollFinishThreshold)) -> {
                    releasedChild.height + mShadowTop?.intrinsicHeight!! + OVERSCROLL_DISTANCE
                }
                releasedChild.y < 0 && canSwipeFromBottom && (yvel < 0 || (yvel == 0f && mScrollPercent > mScrollFinishThreshold)) -> {
                    -(releasedChild.height + mShadowBottom?.intrinsicHeight!! + OVERSCROLL_DISTANCE)
                }
                else -> 0
            }

            mHelper?.settleCapturedViewAt(left, top)
            invalidate()
        }

        override fun onViewDragStateChanged(state: Int) {
            super.onViewDragStateChanged(state)
            if (!mListeners.isNullOrEmpty()) {
                for (listener in mListeners!!) {
                    listener.onDragStateChange(state)
                }
            }
        }
    }

    enum class DragDirection {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        NONE
    }

    internal inner class DragPreCounter {
        private var canDrag = false
        private var firstDragPoint: PointF? = null
        private var lastDragPoint: PointF? = null

        fun getDragDirection(): DragDirection {
            return if (firstDragPoint != null && lastDragPoint != null) {
                val dragX = lastDragPoint?.x!! - firstDragPoint?.x!!
                val dragY = lastDragPoint?.y!! - firstDragPoint?.y!!
                val dirHoriz = abs(dragX) > abs(dragY)

                when {
                    dirHoriz && dragX > 0 -> DragDirection.LEFT
                    dirHoriz && dragX < 0 -> DragDirection.RIGHT
                    !dirHoriz && dragY > 0 -> DragDirection.TOP
                    !dirHoriz && dragY < 0 -> DragDirection.BOTTOM
                    else -> DragDirection.NONE
                }
            } else DragDirection.NONE
        }

        private fun clearDrag() {
            canDrag = false
            firstDragPoint = null
            lastDragPoint = null
        }

        fun setDragPoints(ev: MotionEvent){
            when {
                ev.actionMasked == MotionEvent.ACTION_MOVE -> {
                    if(firstDragPoint == null){
                        firstDragPoint = PointF(ev.x, ev.y)
                    }
                    lastDragPoint = PointF(ev.x, ev.y)
                }
                ev.actionMasked == MotionEvent.ACTION_UP -> {
                    clearDrag()
                }
            }
        }

        fun canCaptureView(): Boolean {
            return if (!canDrag && firstDragPoint != null && lastDragPoint != null) {
                val dir = getDragDirection()

                val dragX = lastDragPoint?.x!! - firstDragPoint?.x!!
                val dragY = lastDragPoint?.y!! - firstDragPoint?.y!!

                if ((canSwipeFromLeft && dir == DragDirection.LEFT && abs(dragX) / width > preDragPercent / 100)
                    || (canSwipeFromRight && dir == DragDirection.RIGHT && abs(dragX) / width > preDragPercent / 100)
                    || (canSwipeFromTop && dir == DragDirection.TOP && abs(dragY) / width > preDragPercent / 100)
                    || (canSwipeFromBottom && dir == DragDirection.BOTTOM && abs(dragY) / width > preDragPercent / 100)
                ) {
                    canDrag = true
                    true
                } else false
            } else canDrag
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        preCounter?.setDragPoints(ev)
        return if (!mEnable) super.onInterceptTouchEvent(ev)
        else mHelper?.shouldInterceptTouchEvent(ev) == true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        preCounter?.setDragPoints(event)
        if (!mEnable) return super.onTouchEvent(event)
        mHelper?.processTouchEvent(event)
        return true
    }

    companion object {
        private val DEFAULT_SCRIM_COLOR = -0x67000000
        private val FULL_ALPHA = 255
        private val DEFAULT_SCROLL_THRESHOLD = 0.4f
        private val OVERSCROLL_DISTANCE = 10
    }
}