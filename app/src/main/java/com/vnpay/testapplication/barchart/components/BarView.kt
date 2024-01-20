package com.vnpay.testapplication.barchart.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.view.View


internal class BarView(context: Context) : View(context) {
    var income: Float = 0F
    var expense: Float = 0F
    var maxIncome: Float = 0F
    var shouldShowShadow: Boolean = false

    var expensePercent: Float = 1F
    var incomePercent: Float = 1F

    val barPaint = Paint().apply {
        val startColor = Color.parseColor("#117115") // Replace with your start color
        val endColor = Color.parseColor("#86CC25")   // Replace with your end color
        val linearGradient = LinearGradient(
            width.toFloat() / 2,
            height.toFloat() * 1.2F,
            width.toFloat() / 2,
            height.toFloat() * (1F - incomePercent * (1 - 0.5F)),
            startColor,
            endColor,
            Shader.TileMode.CLAMP
        )
        shader = linearGradient
        isAntiAlias = true
    }

    val shadowPaint = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#E9F3D7")
    }

    val bgPaint = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#33A1C038")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        drawBackground(canvas)
        drawBars(canvas)
    }

    fun getBarTopPos(): Float {
        if (incomePercent == 0F) {
            return height.toFloat()
        }
        val barHeight = height * incomePercent
        if (barHeight < width.toFloat()) {
            return height.toFloat() - width.toFloat()
        }
        return height.toFloat() * (1 - incomePercent)
    }

    private fun drawBars(canvas: Canvas) {
        if (incomePercent == 0F) {
            return
        }

        val barHeight = height * incomePercent
        val barWidth = width.toFloat()
        var left = 0F
        var top =
            if (barHeight < width.toFloat()) height.toFloat() - width.toFloat() else height.toFloat() * (1 - incomePercent)
        var right = width.toFloat()
        var bottom = height.toFloat()

        if (shouldShowShadow) {
            val shadowWidth = barWidth * (1 + 2 * 0.5F)
            canvas.drawRoundRect(
                left - barWidth * 0.5F,
                top - barWidth * 0.5F,
                right + barWidth * 0.5F,
                bottom + barWidth * 0.5F,
                shadowWidth / 2, shadowWidth / 2, shadowPaint
            )
        }

        canvas.drawRoundRect(
            left, top, right, bottom,
            barWidth / 2, barWidth / 2, barPaint
        )
    }

    private fun drawBackground(canvas: Canvas) {
        canvas.drawRoundRect(
            0F, 0F, width.toFloat(), height.toFloat(),
            width.toFloat() / 2, width.toFloat() / 2,
            bgPaint
        )
    }
}