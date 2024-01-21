package com.vnpay.testapplication.barchart.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.view.View
import android.widget.FrameLayout


class BarView(context: Context) : View(context) {
    var percentage: Float = 0F
    var overPercentage: Float? = null
    var isClicked: Boolean = false

    var barNormalPaint: Paint? = null
    var barActivePaint: Paint? = null
    var barOverPaint: Paint? = null
    var shadowPaint: Paint? = null
    var bgPaint: Paint? = null

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        drawBackground(canvas)
        drawBars(canvas)
    }

    fun getBarTopPos(): Float {
        if (percentage == 0F) {
            return height.toFloat()
        }
        val barHeight = height * percentage
        if (barHeight < width.toFloat()) {
            return height.toFloat() - width.toFloat()
        }
        return height.toFloat() * (1 - percentage)
    }

    private fun drawBars(canvas: Canvas) {
        if (percentage == 0F) {
            return
        }

        if (overPercentage == null) {
            drawNormal(canvas)
        } else {
            drawWhenOver(canvas)
        }


    }

    private fun drawNormal(canvas: Canvas) {
        val barHeight = height.toFloat() * percentage
        val barWidth = width.toFloat()
        var left = 0F
        var top =
            if (barHeight < width.toFloat()) height.toFloat() - width.toFloat() else height.toFloat() * (1 - percentage)
        var right = width.toFloat()
        var bottom = height.toFloat()

        if (isClicked) {
            canvas.drawRoundRect(
                left - barWidth * 0.5F,
                top - barWidth * 0.5F,
                right + barWidth * 0.5F,
                bottom + barWidth * 0.5F,
                shadowPaint!!
            )
            canvas.drawRoundRect(
                left, top, right, bottom, barActivePaint!!
            )
        } else {
            canvas.drawRoundRect(
                left, top, right, bottom, barNormalPaint!!
            )
        }

    }

    private fun drawWhenOver(canvas: Canvas) {
        canvas.drawRoundRect(
            0F, 0F, width.toFloat(), height.toFloat(), barNormalPaint!!
        )

        val corner = width.toFloat() / 2
        val corners = floatArrayOf(
            corner, corner,
            corner, corner,
            0f, 0f,
            0f, 0f
        )
        val overHeight = (overPercentage ?: 0F) * height.toFloat()
        val rect = RectF(0F, 0F, width.toFloat(), overHeight)

        val path = Path()
        path.addRoundRect(rect, corners, Path.Direction.CW)
        canvas.drawPath(path, barOverPaint!!)
    }

    private fun drawBackground(canvas: Canvas) {
        canvas.drawRoundRect(
            0F, 0F, width.toFloat(), height.toFloat(), bgPaint!!
        )
    }
}