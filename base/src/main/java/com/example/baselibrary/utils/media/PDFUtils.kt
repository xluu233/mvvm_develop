package com.example.baselibrary.utils.media

import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.print.PrintAttributes
import com.example.baselibrary.utils.other.FilePath
import java.io.*

/**
 * @ClassName PdfUtils
 * @Description
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/3/19 17:33
 */
object PDFUtils {

    const val StorageName = "好多曲谱"

    /**
     * 将Bitmap保存为PDF文件在Download目录下
     *
     * @param bitmaps
     * @param fileName 文件名
     */
    fun saveBitmapForPdf(bitmaps: ArrayList<Bitmap>, fileName: String): File {
        val doc = PdfDocument()
        val pageWidth: Int = PrintAttributes.MediaSize.ISO_A4.widthMils * 72 / 1000
        val scale = pageWidth.toFloat() / bitmaps[0].width.toFloat()
        val pageHeight = (bitmaps[0].height * scale).toInt()
        val matrix = Matrix()
        matrix.postScale(scale, scale)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        for (i in 0 until bitmaps.size) {
            val newPage = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, i).create()
            val page = doc.startPage(newPage)
            val canvas = page.canvas
            canvas.drawBitmap(bitmaps[i], matrix, paint)
            doc.finishPage(page)
        }

        val file = File(FilePath.getExternalDownloadPath(StorageName), fileName)
        var outputStream: FileOutputStream? = null
        try {
            outputStream = FileOutputStream(file)
            doc.writeTo(outputStream)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            doc.close()
            try {
                outputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return file
    }

}