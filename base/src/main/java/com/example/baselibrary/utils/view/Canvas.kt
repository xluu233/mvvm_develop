package com.example.baselibrary.utils.view

import android.graphics.Canvas
import android.graphics.Paint


inline fun Canvas.drawCenterVerticalText(text: String, centerX: Float, centerY: Float, paint: Paint) =
  drawCenterText(text, centerX, centerY, paint, Paint.Align.LEFT)

inline fun Canvas.drawCenterText(
  text: String,
  centerX: Float,
  centerY: Float,
  paint: Paint,
  textAlign: Paint.Align = Paint.Align.CENTER
) {
  val textAlignTemp = paint.textAlign
  paint.textAlign = textAlign
  val fontMetrics = paint.fontMetrics
  val baseline = centerY + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
  drawText(text, centerX, baseline, paint)
  paint.textAlign = textAlignTemp
}
