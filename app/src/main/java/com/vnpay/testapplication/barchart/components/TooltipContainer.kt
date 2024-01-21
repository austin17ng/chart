package com.vnpay.testapplication.barchart.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.FrameLayout
import com.vnpay.testapplication.Utils


class TooltipContainer(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var message: String? = null
    private var triangleX: Float = 0F
    private var triangleY: Float = 0F

    var spaceToBarChart: Float = 0F

    var tooltipPaddingVertical = Utils.dpToPx(context, 4F)
    var tooltipPaddingHorizontal = Utils.dpToPx(context, 8F)
    var tooltipMarginBottom = Utils.dpToPx(context, 4F)
    var triangleHeight = Utils.dpToPx(context, 5F)

    val textPaint = Paint().apply {
        isAntiAlias = true
        textSize = Utils.spToPx(context, 12F)
        color = Color.parseColor("#121212")
    }

    val paintTooltip = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#F8FFEC")
        setShadowLayer(
            Utils.dpToPx(context, 4F), 0F,
            Utils.dpToPx(context, 4F), Color.parseColor("#29000000")
        )
    }

    init {
        setWillNotDraw(false)
    }

    fun showTooltip(x: Float, y: Float, message: String) {
        this.triangleX = x
        this.triangleY = y - tooltipMarginBottom
        this.message = message
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        if (message != null) {
            drawTooltip(canvas)
        }

    }


    private fun drawTooltip(canvas: Canvas) {
        val bounds = Rect()
        textPaint.getTextBounds(message, 0, message!!.length, bounds)

        val path = Path()
        path.moveTo(triangleX - triangleHeight, triangleY - triangleHeight)
        path.lineTo(triangleX, triangleY)
        path.lineTo(triangleX + triangleHeight, triangleY - triangleHeight)

        val rectTop =
            triangleY - triangleHeight - bounds.height().toFloat() - tooltipPaddingVertical * 2
        var rectRight = triangleX + bounds.width().toFloat() * 2 / 3 + tooltipPaddingHorizontal
        var rectLeft = triangleX - bounds.width().toFloat() / 3 - tooltipPaddingHorizontal
        val rectBottom = triangleY - triangleHeight

        if (rectRight >= width + spaceToBarChart) {
            val spaceExtra = rectRight - (width + spaceToBarChart)
            rectRight = rectRight - spaceExtra - spaceToBarChart / 3
            rectLeft = rectLeft - spaceExtra - spaceToBarChart / 3
        }

        if (rectLeft <= -spaceToBarChart) {
            val spaceExtra = -spaceToBarChart - rectLeft
            rectRight += spaceExtra + spaceToBarChart / 3
            rectLeft += spaceExtra + spaceToBarChart / 3
        }

        path.addRoundRect(
            rectLeft,
            rectTop,
            rectRight,
            rectBottom,
            10F,
            10F,
            Path.Direction.CW
        )

        if (rectTop < 0) {
            canvas.translate(0F, 0F - rectTop)
        }

        canvas.drawPath(path, paintTooltip)

        canvas.drawText(
            message!!,
            rectLeft + tooltipPaddingHorizontal,
            triangleY - triangleHeight - tooltipPaddingVertical,
            textPaint
        )

    }
}