package com.vnpay.testapplication.barchart.components

import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.text.StaticLayout

fun Canvas.drawMultilineTextCenter(text: String, x: Float, y: Float, paint: Paint) {
    val fm = paint.fontMetrics
    var yPos = y - fm.top
    for (line in text.split("\n")) {
        val xPos = x - paint.measureText(line)/2
        this.drawText(line, xPos, yPos, paint)
        yPos += fm.leading + fm.bottom - fm.top
    }
}

fun Canvas.drawRoundRect(left: Float, top: Float, right: Float, bottom: Float, paint: Paint) {
    if (paint is VerticalGradientPaint) {
        drawRoundRectGra(left, top, right, bottom, paint)
    } else {
        val radius = (right - left) / 2
        this.drawRoundRect(left, top, right, bottom, radius, radius, paint)
    }
}

fun Canvas.drawRoundRectGra(
    left: Float, top: Float, right: Float, bottom: Float, paint: VerticalGradientPaint
) {
    val linearGradient = LinearGradient(
        (left + right) / 2,
        bottom,
        (left + right) / 2,
        top,
        paint.bottomColor,
        paint.topColor,
        Shader.TileMode.CLAMP
    )
    paint.apply {
        shader = linearGradient
    }

    val radius = (right - left) / 2
    this.drawRoundRect(
        left, top, right, bottom, radius, radius, paint
    )

}

class VerticalGradientPaint(val bottomColor: Int, val topColor: Int) : Paint()