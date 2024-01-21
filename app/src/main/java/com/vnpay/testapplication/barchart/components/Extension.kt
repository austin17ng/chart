package com.vnpay.testapplication.barchart.components

import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader

fun Canvas.drawMultilineTextCenter(text: String, x: Float, y: Float, paint: Paint) {
    //todo
    //trick
    val fontHeight = paint.fontMetrics.descent - paint.fontMetrics.ascent
    var yPos = y - 1F
    for (line in text.split("\n")) {
        val xPos = x - paint.measureText(line)/2
        yPos += fontHeight
        this.drawText(line, xPos, yPos, paint)
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