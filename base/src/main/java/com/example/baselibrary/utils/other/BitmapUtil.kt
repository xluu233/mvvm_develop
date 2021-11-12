package com.example.baselibrary.utils.other

import android.content.Context
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.nio.ByteBuffer


/**
 * @ClassName BitmapUtil
 * @Description Bitmap操作工具类
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/7/13 15:29
 */
object BitmapUtil {

//    BitmapFactory会导致图像旋转
//    先记录旋转角度，在转为bitmap之后再旋转回来

    /**
     *  file转bitmap
     */
    fun convertToBitmap(file: File?): Bitmap? {
        if (file==null) return null
        val rotate = getRotateDegree(file.absolutePath)
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        return rotateBitmap(bitmap, rotate)
    }

    /**
     *  filePath转bitmap
     */
    fun convertToBitmap(path: String?): Bitmap? {
        val rotate = getRotateDegree(path)
        val bitmap = BitmapFactory.decodeFile(path)
        return rotateBitmap(bitmap, rotate)
    }

    /**
     *  Uri转Bitmap
     */
    fun convertToBitmap(uri: Uri?, context: Context): Bitmap? {
        if (uri==null) return null
        val fd = context.contentResolver.openFileDescriptor(uri, "r") ?: return null
        val bitmap = BitmapFactory.decodeFileDescriptor(fd.fileDescriptor)
        fd.close()
        //val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        return bitmap
    }

    /**
     *  Assert资源文件转Bitmap
     */
    fun assert2Bitmap(fileName: String, context: Context) : Bitmap?{
        var ins: InputStream? = null
        try {
            ins = context.resources.assets.open(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return BitmapFactory.decodeStream(ins)
    }

    /**
     *  获取图片的旋转角度
     * 只能通过原始文件获取，如果已经进行过bitmap操作无法获取。
     */
    private fun getRotateDegree(path: String?): Float {
        var result = 0f
        if (path.isNullOrEmpty()) return result

        try {
            val exif = ExifInterface(path)
            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> result = 90f
                ExifInterface.ORIENTATION_ROTATE_180 -> result = 180f
                ExifInterface.ORIENTATION_ROTATE_270 -> result = 270f
            }
        } catch (ignore: IOException) {
            return result
        }
        return result
    }

    /**
     *  处理图片旋转
     */
    private fun rotateBitmap(bitmap: Bitmap?, rotate: Float): Bitmap? {
        if (bitmap == null) return null
        if (rotate==0f) return bitmap

        val w = bitmap.width
        val h = bitmap.height

        // Setting post rotate to 90
        val mtx = Matrix()
        mtx.postRotate(rotate)

        val outBit = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true)
        bitmap.recycle()
        return outBit
    }

    /**
     *  Bitmap压缩
     * @param quality 0-100
     */
    fun Bitmap.compressQuality(quality: Int = 90):Bitmap = apply{
        val bos = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.JPEG, quality, bos)
        val bytes = bos.toByteArray()
        BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }


    /**
     *  Bitmap转ByteArray
     */
    fun convertToByteArray(bitmap: Bitmap?):ByteArray? {
        if (bitmap==null) return null
        val bytes: Int = bitmap.byteCount
        val buf: ByteBuffer = ByteBuffer.allocate(bytes)
        bitmap.copyPixelsToBuffer(buf)

        return buf.array()
    }

    /**
     *  Bitmap转ByteBuffer
     */
    fun convertToByteBuffer(bitmap: Bitmap?):ByteBuffer? {
        if (bitmap==null) return null
        val bytes: Int = bitmap.byteCount
        val buf: ByteBuffer = ByteBuffer.allocate(bytes)
        bitmap.copyPixelsToBuffer(buf)
        return buf
    }

    /**
     *  byteArray转Bitmap
     */
    fun byte2Bitmap(byteArray: ByteArray?):Bitmap?{
        if (byteArray==null) return null
        val bitmap : Bitmap ?= BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size);
        return bitmap
    }

    /**
     * 网络url转bitmap
     */
    fun getUrlBitmap(url: String?): Bitmap? {
        var bm: Bitmap? = null
        try {
            val iconUrl = URL(url)
            val conn: URLConnection = iconUrl.openConnection()
            val http: HttpURLConnection = conn as HttpURLConnection
            val length: Int = http.contentLength
            conn.connect()
            // 获得图像的字符流
            val `is`: InputStream = conn.getInputStream()
            val bis = BufferedInputStream(`is`, length)
            bm = BitmapFactory.decodeStream(bis)
            bis.close()
            `is`.close() // 关闭流
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bm
    }

    /**
     *  最佳缩放Bitmap
     */
    fun scaleBitmap(bm: Bitmap?, width: Int, height: Int): Bitmap? {
        if (bm==null || bm.isRecycled){
            return null
        }
        val srcWidth = bm.width
        val srcHeight = bm.height
        val widthScale = width * 1.0f / srcWidth
        val heightScale = height * 1.0f / srcHeight
        val matrix = Matrix()
        matrix.postScale(widthScale, heightScale, 0f, 0f)
        // 如需要可自行设置 Bitmap.Config.RGB_8888 等等
        val bmpRet = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        val canvas = Canvas(bmpRet)
        val paint = Paint()
        canvas.drawBitmap(bm, matrix, paint)
        return bmpRet
    }


}