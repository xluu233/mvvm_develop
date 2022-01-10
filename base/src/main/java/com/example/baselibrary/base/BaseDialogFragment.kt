package com.example.baselibrary.base

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.baselibrary.R
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import android.view.WindowManager





/**
 * 基础Dialog封装
 */
abstract class BaseDialogFragment<VB : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : DialogFragment() {

    val TAG by lazy {
        this.javaClass.name
    }

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    private var isLoaded = false
    lateinit var mContext: Context

    private var cancel:Boolean = true
    private var gravity:Int = Gravity.CENTER
    private var width:Int = WRAP_CONTENT
    private var height:Int = WRAP_CONTENT

    @StyleRes
    private var animation: Int = R.style.dialogAnimation_center
    @StyleRes
    private var style:Int = R.style.DialogThemeTrans
    @DrawableRes
    private var layoutBackground:Int = android.R.color.transparent
    //展示背景阴影
//    private var showWindowsShadow:Boolean = false
    //背景阴影透明度
//    private var dimAmount:Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, style)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setWindowAnimations(animation)
//            if (showWindowsShadow){
//                val windowParams: WindowManager.LayoutParams = attributes
//                windowParams.dimAmount = dimAmount
//                attributes = windowParams
//            }
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.apply {
            setCancelable(cancel)
            setCanceledOnTouchOutside(cancel)
            isCancelable = cancel
        }
        dialog?.window?.apply {
            setLayout(width,height)
            setGravity(gravity)
            setBackgroundDrawableResource(layoutBackground)
        }
        //binding.root.setBackgroundResource(layoutBackground)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            initView()
            initData()
        }
    }

    fun initParams(cancel:Boolean = this.cancel,
                   width:Int = this.width,
                   height:Int = this.height,
                   @DrawableRes layoutBackground:Int = this.layoutBackground,
                   gravity: Int = this.gravity,
                   @StyleRes anim:Int = this.animation,
                   @StyleRes style:Int = this.style,
                   //showWindowsShadow:Boolean = false
    ) = apply{
        this.animation = anim
        this.style = style
        this.cancel = cancel
        this.width = width
        this.height = height
        this.layoutBackground = layoutBackground
        this.gravity = gravity
        //this.dimAmount = if (showWindowsShadow) 0.4f else 0f
    }

    abstract suspend fun initView()

    abstract suspend fun initData()

    override fun onDestroy() {
        _binding = null
        lifecycleScope.cancel()
        super.onDestroy()
    }

}