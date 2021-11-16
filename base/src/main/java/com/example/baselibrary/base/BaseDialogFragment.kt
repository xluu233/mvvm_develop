package com.example.baselibrary.base

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.example.baselibrary.R
import com.example.baselibrary.lifecycle.ActivityStack


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
    private var animation: Int = R.style.dialogAnimation
    @StyleRes
    private var style:Int = R.style.DialogTheme
    @DrawableRes
    private var background:Int = android.R.color.transparent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, style)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setWindowAnimations(animation)
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
            setGravity(Gravity.CENTER)
            setBackgroundDrawableResource(background)
        }
    }

    override fun onResume() {
        super.onResume()
        initView()
        initData()
    }

    fun initParams(cancel:Boolean = this.cancel,
                   width:Int = this.width,
                   height:Int = this.height,
                   @DrawableRes resId:Int = this.background,
                   gravity: Int = this.gravity,
                   @StyleRes anim:Int = this.animation,
                   @StyleRes style:Int = this.style
    ) = apply{
        this.animation = anim
        this.style = style
        this.cancel = cancel
        this.width = width
        this.height = height
        this.background = resId
        this.gravity = gravity
    }

    abstract fun initView()

    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}