package com.example.baselibrary.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.baselibrary.R
import com.example.baselibrary.utils.view.dp
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

abstract class BaseBottomSheetDialogFragment<VB : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : BottomSheetDialogFragment() {


    val TAG by lazy {
        this.javaClass.name
    }

    lateinit var mContext: Context
    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    //是否展示背景阴影
    var showShadow = false

    //是否能否点击外部取消
    var cancel = true

    //默认状态：折叠
    var defaultState = BottomSheetBehavior.STATE_COLLAPSED

    //默认折叠高度
    var peekHeight:Int = 100.dp.toInt()

    //布局默认背景
    @DrawableRes
    var layoutBackground:Int = android.R.color.transparent
    @StyleRes
    var animation: Int = R.style.dialogAnimation_bottom
    @StyleRes
    var style:Int = R.style.DialogThemeShadow

    var width:Int = ViewGroup.LayoutParams.WRAP_CONTENT
    var height:Int = ViewGroup.LayoutParams.WRAP_CONTENT


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,style)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setWindowAnimations(animation)
            if (!showShadow){
                val windowParams: WindowManager.LayoutParams = attributes
                windowParams.dimAmount = 0f
                attributes = windowParams
            }
        }
        //拿到系统的 bottom_sheet
        val view: FrameLayout = dialog?.findViewById(R.id.design_bottom_sheet)!!
        //设置view高度
        view.layoutParams.height = height
        //获取behavior
        val behavior = BottomSheetBehavior.from(view)
        //设置弹出高度
        behavior.peekHeight = peekHeight
        //设置展开状态
        //behavior.state = BottomSheetBehavior.STATE_EXPANDED

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
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
            setBackgroundDrawableResource(layoutBackground)
        }
    }


    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            initView()
            initData()
        }
    }

    abstract suspend fun initData()

    abstract suspend fun initView()


}