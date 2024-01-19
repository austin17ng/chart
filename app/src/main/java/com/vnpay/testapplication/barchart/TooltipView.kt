package com.vnpay.testapplication.barchart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.FrameLayout

class TooltipView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    var message: String? = null
    var xPos: Float = 0F
    var yPos: Float = 0F

    init {
        setWillNotDraw(false)
    }

    fun showTooltip(x: Float, y: Float, message: String) {
        this.xPos = x
        this.yPos = y - 10F
        this.message = message
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (message != null && canvas != null) {
            drawTooltip(canvas)
        }

    }

    private fun drawTooltip(canvas: Canvas) {
        val paintTooltip = Paint().apply {
            isAntiAlias = true
            color = Color.parseColor("#F8FFEC")
        }
        val path = Path()
        path.moveTo(xPos - 10F, yPos - 10F)
        path.lineTo(xPos, yPos)
        path.lineTo(xPos + 10, yPos - 10F)
        canvas.drawPath(path, paintTooltip)

    }

    companion object {

    }

}