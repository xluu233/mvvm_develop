package com.example.baselibrary.view.photoscontentview

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ImageView
import com.example.baselibrary.R
import kotlin.math.min

class PhotosContentView<T> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    companion object{
        const val MODE_FILL = 0 //填充模式，类似于微信
        const val MODE_GRID = 1 //网格模式，类似于QQ，4张图 2X2布局
    }

    private var singleImageSize = 250 // 单张图片时的最大大小,单位dp
    private var singleImageRatio = 1.0f // 单张图片的宽高比(宽/高)
    private var gridSpacing = 6 // 宫格间距，单位dp
    private var maxImageSize = 9 // 最大图片数
    private var mode = MODE_FILL // 默认填充模式

    private var columnCount = 0 // 列数
    private var rowCount = 0 // 行数
    private var gridWidth = 0 // 宫格宽度
    private var gridHeight = 0// 宫格高度

    private val imageViewList = mutableListOf<ImageView>()
    private var imageDataList: MutableList<T>? = null
    private var adapter: PhotosContentViewAdapter<T>? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PhotosContentView)
        singleImageSize = typedArray.getDimensionPixelSize(R.styleable.PhotosContentView_singleImgSize,
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, singleImageSize.toFloat(), resources.displayMetrics).toInt())
        singleImageRatio = typedArray.getFloat(R.styleable.PhotosContentView_singleImageRatio, singleImageRatio)
        gridSpacing = typedArray.getDimension(R.styleable.PhotosContentView_imgGap,
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, gridSpacing.toFloat(), resources.displayMetrics)).toInt()
        maxImageSize = typedArray.getInt(R.styleable.PhotosContentView_maxSize, maxImageSize)
        mode = typedArray.getInt(R.styleable.PhotosContentView_showStyle, mode)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = MeasureSpec.getSize(widthMeasureSpec)
        val height: Int
        val totalWidth = width - paddingStart - paddingEnd
        if (!imageDataList.isNullOrEmpty()) {
            if (maxImageSize == 1 || imageDataList?.size == 1) {
                gridWidth = min(totalWidth, singleImageSize)
                gridHeight = (gridWidth.toFloat() / singleImageRatio).toInt()
                if (gridHeight > singleImageSize) {
                    val radio = gridHeight.toFloat() / singleImageSize
                    gridWidth = (gridWidth * radio).toInt()
                    gridHeight = singleImageSize
                }
            } else {
                gridWidth = (totalWidth - gridSpacing * (columnCount - 1)) / columnCount
                gridHeight = gridWidth
            }
            width = gridWidth * columnCount + gridSpacing * (columnCount - 1) + paddingLeft + paddingRight
            height = gridHeight * rowCount + gridSpacing * (rowCount - 1) + paddingTop + paddingBottom
            setMeasuredDimension(width, height)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        imageDataList?.let {
            for (i in it.indices) {
                val childrenView = getChildAt(i) as ImageView
                val columnNum = i % columnCount
                val rowNum = i / columnCount
                val left = (gridWidth + gridSpacing) * columnNum + paddingLeft
                val top = (gridHeight + gridSpacing) * rowNum + paddingTop
                val right = left + gridWidth
                val bottom = top + gridHeight
                childrenView.layout(left, top, right, bottom)
                adapter?.onDisplayImage(context, childrenView, it[i])
            }
        }
    }

    fun setImageData(lists: MutableList<T>) {
        if (lists.isNullOrEmpty()) {
            this.visibility = GONE
            return
        } else {
            this.visibility = VISIBLE
        }
        var imageCount = lists.size
        var tempDataList = lists
        if (maxImageSize in 1 until imageCount) {
            tempDataList = tempDataList.subList(0, maxImageSize)
            imageCount = tempDataList.size
        }

        rowCount = imageCount / 3 + if (imageCount % 3 == 0) 0 else 1
        columnCount = 3
        if (mode == MODE_GRID && imageCount == 4) {
            rowCount = 2
            columnCount = 2
        }

        if (imageDataList == null) {
            for (i in 0 until imageCount) {
                val imageView = getImageView(i) ?: return
                addView(imageView, generateDefaultLayoutParams())
            }
        } else {
            val oldViewCount: Int = imageDataList?.size ?: 0
            if (oldViewCount > imageCount) {
                removeViews(imageCount, oldViewCount - imageCount)
            } else if (oldViewCount < imageCount) {
                for (i in oldViewCount until imageCount) {
                    val iv = getImageView(i) ?: return
                    addView(iv, generateDefaultLayoutParams())
                }
            }
        }

        imageDataList = tempDataList
        requestLayout()
    }

    private fun getImageView(position: Int): ImageView? {
        val imageView: ImageView?
        if (position < imageViewList.size) {
            imageView = imageViewList[position]
        } else {
            imageView = adapter?.generateImageView(context)
            imageView?.let {
                imageViewList.add(imageView)
                imageView.setOnClickListener { view ->
                    imageDataList?.let {
                        adapter?.onItemImageClick(context, view as ImageView, position, it)
                    }
                }
                imageView.setOnLongClickListener { view ->
                    imageDataList?.let {
                        return@setOnLongClickListener adapter?.onItemImageLongClick(context, view as ImageView,
                            position, it) ?: false
                    }
                    return@setOnLongClickListener false
                }
            }
        }
        return imageView
    }

    fun setAdapter(adapter: PhotosContentViewAdapter<T>) {
        this.adapter = adapter
    }

    fun setGap(gridSpacing: Int) {
        this.gridSpacing = gridSpacing
    }

    fun setSingleImgSize(singleImgSize: Int) {
        this.singleImageSize = singleImgSize
    }

    fun setMaxSize(maxSize: Int) {
        this.maxImageSize = maxSize
    }

    fun getImageViewList() = imageViewList
}