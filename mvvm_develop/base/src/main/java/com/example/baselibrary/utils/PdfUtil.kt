package com.example.baselibrary.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Environment
import android.print.PrintAttributes
import android.provider.MediaStore
import java.io.*

/**
 * @ClassName PdfUtils
 * @Description PDF文件工具类
 * @Author AlexLu_1406496344@qq.com
 * @Date 2021/3/19 17:33
 */
object PdfUtil {

    const val pdf_save_path = "ABC啦啦啦"

    /**
     *  Bitmap保存为PDF文件
     * 目录：外部存储->Download->pdf_save_path
     */
    fun saveBitmapForPdf(bitmaps: ArrayList<Bitmap>, fileName: String, context: Context): File? {
        val doc = PdfDocument()
        val pageWidth: Int = PrintAttributes.MediaSize.ISO_A4.getWidthMils() * 72 / 1000
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues()
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, getMediaStorePath())
            val uri = context.contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values)
            uri?.also {
                val outputStream = context.contentResolver.openOutputStream(it)
                outputStream?.also { os ->
                    try {
                        doc.writeTo(os)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } finally {
                        doc.close()
                        os.close()
                        try {
                            outputStream.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }

                }
            }
            return File(uri.toString())

        } else {
            val filePath = FileUtil.getExternalDownloadPath(pdf_save_path)
            val file = File(filePath, fileName)
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

    private fun getMediaStorePath(): String {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            // full path
            "${Environment.getExternalStorageDirectory().absolutePath}/" + "${Environment.DIRECTORY_DOWNLOADS}/${pdf_save_path}/"
        } else {
            // relative path
            "${Environment.DIRECTORY_DOWNLOADS}/${pdf_save_path}/"
        }
    }
}