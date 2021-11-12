package com.example.baselibrary.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * 双指缩放Imageview
 */
public class ZoomImageView extends androidx.appcompat.widget.AppCompatImageView implements View.OnTouchListener{
    private static final String TAG = ZoomImageView.class.getSimpleName();
    private boolean support_touch = true;//支持触摸事件

    private int mode = 0;// 初始状态
    private static final int MODE_DRAG = 1;//平移
    private static final int MODE_ZOOM = 2;//缩放

    private static final float MAX_SCALE = 4f, MIN_SCALE = 1f;//最大放大倍数，最小放大倍数
    float total_scale = MIN_SCALE , current_scale;//total_scale缩放范围2-1，当小于1回弹到1；当大于2回弹到2

    private Matrix matrixNow = new Matrix();
    private Matrix matrixBefore = new Matrix();
    private Matrix mInitializationMatrix = new Matrix();//初始缩放值

    private PointF actionDownPoint = new PointF();//点击点
    private PointF dragPoint = new PointF();//平移点
    private PointF startPoint = new PointF();//滑动点
    private PointF mInitializationScalePoint = new PointF();//初始缩放点
    private PointF mCurrentScalePoint = new PointF(0, 0);//当前缩放点
    private float startDis;//滑动开始距离
    /** 两个手指的中间点 */
    private PointF midPoint = new PointF(0,0);

    public ZoomImageView(Context context) {
        this(context, null);
    }
    public ZoomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }
    private void initData() {
        if (support_touch) {
            setOnTouchListener(this);
        }
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ZoomImageView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int[] viewLocation = new int[2];
                ZoomImageView.this.getLocationInWindow(viewLocation);
                int viewX = viewLocation[0]; // x 坐标；有bug，在viewpage中。
                int viewY = viewLocation[1]; // y 坐标
                mInitializationScalePoint.set( ZoomImageView.this.getWidth() / 2, viewY + ZoomImageView.this.getHeight() / 2);//初始化缩放位置
                //xLog.i("yangxun", "控件 宽：" + mInitializationScalePoint.x + "高：" + mInitializationScalePoint.y);
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);//清空画布
        if (matrixNow != null) {
            canvas.concat(matrixNow);
//            canvas.setMatrix(matrixNow);//显示有问题
        }
        super.onDraw(canvas);
    } @Override
    public void setImageMatrix(Matrix matrix) {
        matrixNow.set(matrix);
        invalidate();
    }


    public void resetImageMatrix() {
        this.matrixNow.set(mInitializationMatrix);
        invalidate();
    }
    //最小重置数值
    private void resetToMinStatus(){
        mCurrentScalePoint.set(0, 0);
        total_scale = MIN_SCALE;
    }
    //最大重置数值
    private void resetToMaxStatus(){
        total_scale = MAX_SCALE;
    }
    public float getInitializationBitmapHeight(){
        return getHeight()*total_scale;
    }
    public float getInitializationBitmapWidth(){
        return getWidth()*total_scale;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (total_scale != 1) {
            getParent().requestDisallowInterceptTouchEvent(true);//触摸事件请求拦截
        }

        /** 通过与运算保留最后八位 MotionEvent.ACTION_MASK = 255 */
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN://单指触摸
                mode = MODE_DRAG;
                matrixBefore.set(getImageMatrix());
                matrixNow.set(getImageMatrix());
                dragPoint.set(event.getX(), event.getY());
                actionDownPoint.set(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_POINTER_DOWN://双指触摸
                getParent().requestDisallowInterceptTouchEvent(true);//触摸事件请求拦截
                mode = MODE_ZOOM;
                startPoint.set(event.getX(), event.getY());
                startDis = distance(event);
                /** 计算两个手指间的中间点 */
                if (startDis > 10f) {
                    //记录当前ImageView的缩放倍数
                    matrixBefore.set(getImageMatrix());
                    matrixNow.set(getImageMatrix());
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == MODE_DRAG && total_scale > 1) {
                    float dx = event.getX() - dragPoint.x;
                    float dy = event.getY() - dragPoint.y;
                    dragPoint.set(event.getX(), event.getY());
                    imgTransport(dx, dy);
                } else if (mode == MODE_ZOOM) {//缩放
                    float endDis = distance(event);
                    midPoint = mid(event);
                    if (endDis > 10f) {
                        current_scale = endDis / startDis;//缩放倍数
                        total_scale *= current_scale;
                        matrixNow.postScale(current_scale, current_scale, midPoint.x, midPoint.y);
                        invalidate();
                    }
                    startDis = endDis;
                }
                break;

            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);//触摸事件请求取消拦截
                mode = 0;
                if(mode == MODE_DRAG){
                    checkClick(event.getX(),event.getY(), actionDownPoint.x, actionDownPoint.y);
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                checkZoomValid();
                mode = 0;
                break;

        }
        return true;
    }
    /**
     * 平移图片
     * @param x
     * @param y
     */
    public void imgTransport(float x,float y){
        mCurrentScalePoint.set(mCurrentScalePoint.x + x, mCurrentScalePoint.y + y);
        if (mCurrentScalePoint.x >= ((total_scale - 1) * getWidth()) / 2) {
            mCurrentScalePoint.x = ((total_scale - 1) * getWidth()) / 2;
            x = 0;
        } else {
            if (mCurrentScalePoint.x <= -((total_scale - 1) * getWidth()) / 2) {
                mCurrentScalePoint.x = -((total_scale - 1) * getWidth()) / 2;
                x = 0;
            }
        }
        if (mCurrentScalePoint.y >= ((total_scale - 1) * getHeight()) / 2) {
            mCurrentScalePoint.y = ((total_scale - 1) * getHeight()) / 2;
            y = 0;
        } else {
            if (mCurrentScalePoint.y <= -((total_scale - 1) * getHeight()) / 2) {
                mCurrentScalePoint.y = -((total_scale - 1) * getHeight()) / 2;
                y = 0;
            }
        }
        Log.i(TAG, "mCurrentScalePoint.x:" + mCurrentScalePoint.x + "   x:" + x);
        matrixNow.postTranslate(x, y);
        invalidate();
    }
    private boolean checkZoomValid() {
        if(mode == MODE_ZOOM){
            if(total_scale>MAX_SCALE){
                resetToMaxStatus();
                matrixNow.set(mInitializationMatrix);
                matrixNow.postScale(MAX_SCALE, MAX_SCALE, mInitializationScalePoint.x, mInitializationScalePoint.y);
                matrixNow.postTranslate(mCurrentScalePoint.x, mCurrentScalePoint.y);
                invalidate();
                return false;
            }else if(total_scale<MIN_SCALE){
                resetToMinStatus();
                matrixNow.set(mInitializationMatrix);
                invalidate();
                return false;
            }
            invalidate();
        }
        return true;
    }

    private float distance(MotionEvent event) {
        float dx = event.getX(1) - event.getX(0);
        float dy = event.getY(1) - event.getY(0);
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    private PointF mid(MotionEvent event) {
        float midX = (event.getX(1) + event.getX(0)) / 2;
        float midY = (event.getY(1) + event.getY(0)) / 2;
        return new PointF(midX, midY);
    }

    boolean checkClick(float last_x,float last_y,float now_x,float now_y){
        float x_d = Math.abs(last_x - now_x);
        float y_d = Math.abs(last_y - now_y);
        if(x_d<10 && y_d<10){//点击事件
            //处理单击事件
            //return false;
            Log.d("zoomimageview","单击事件");
        }
        if (total_scale == 1) {
            matrixNow.set(mInitializationMatrix);
            invalidate();
        }
        return false;
    }
    @Override
    public Matrix getImageMatrix() {
        return matrixNow;
    }
    public Matrix getBeforeImageMatrix() {
        return matrixBefore;
    }


}