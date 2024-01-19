package com.vnpay.testapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.Region
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi

class TestView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    init {

    }

    private val paintRed = Paint().apply {
        color = Color.RED
        isAntiAlias = true
    }

    private val paintBlack = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun draw(canvas: Canvas) {
//        drawOldVersion(canvas!!)

        canvas.drawRect(-20F, -20F, width.toFloat() + 20F, height.toFloat() + 20F, paintRed)
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paintBlack)
        super.draw(canvas)
    }

    private fun drawOldVersion(canvas: Canvas) {
        val rect = canvas.clipBounds
        rect.inset(-20, -20)
        canvas.clipRect(rect, Region.Op.REPLACE)

        canvas.drawRect(-20F, -20F, width.toFloat() + 20F, height.toFloat() + 20F, paintRed)

        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), paintBlack)
    }
}