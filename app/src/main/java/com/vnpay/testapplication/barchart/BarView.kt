package com.vnpay.testapplication.barchart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Shader
import android.widget.LinearLayout


internal class BarView(context: Context?) : LinearLayout(context) {
    var income: Float = 0F
    var expense: Float = 0F
    var maxIncome: Float = 0F

    var expensePercent: Float = 1F
    var incomePercent: Float = 1F

    init {
        setWillNotDraw(false)
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

        val paint = Paint().apply {
            shader = linearGradient
            isAntiAlias = true
        }

        val barHeight = height * incomePercent
        if (barHeight < width.toFloat()) {
            canvas.drawRoundRect(
                0f, height.toFloat() - width.toFloat(), width.toFloat(), height.toFloat(),
                width.toFloat() / 2, width.toFloat() / 2,
                paint
            )
        } else {
            val top = height.toFloat() * (1 - incomePercent)
            val bottom = height.toFloat()
            canvas.drawRoundRect(
                0f, top, width.toFloat(), bottom,
                width.toFloat() / 2, width.toFloat() / 2,
                paint
            )
        }
    }

    private fun drawBackground(canvas: Canvas) {
        val paint = Paint().apply {
            isAntiAlias = true
            color = Color.parseColor("#33A1C038")
        }

        canvas.drawRoundRect(
            0F, 0F, width.toFloat(), height.toFloat(),
            width.toFloat() / 2, width.toFloat() / 2,
            paint
        )
    }
}