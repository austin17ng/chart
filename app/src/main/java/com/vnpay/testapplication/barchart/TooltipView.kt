package com.vnpay.testapplication.barchart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.marginBottom
import com.vnpay.testapplication.Utils


class TooltipView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    var message: String? = null
    var xPos: Float = 0F
    var yPos: Float = 0F

    private var tooltipPaddingVertical = 0F
    private var tooltipPaddingHorizontal = 0F
    private var tooltipMarginBottom = 0F
    private var toolTextSize = 0F
    private var triangleSize = 0F

    init {
        setWillNotDraw(false)
        tooltipPaddingVertical = Utils.dpToPx(context, DEFAULT_PADDING_VERTICAL)
        tooltipPaddingHorizontal = Utils.dpToPx(context, DEFAULT_PADDING_HORIZONTAL)
        toolTextSize = Utils.dpToPx(context, DEFAULT_TEXT_SIZE)
        triangleSize = Utils.dpToPx(context, DEFAULT_TRIANGLE_SIZE)
        tooltipMarginBottom = Utils.dpToPx(context, DEFAULT_TOOLTIP_MARGIN_BOTTOM)
    }

    fun showTooltip(x: Float, y: Float, message: String) {
        this.xPos = x
        this.yPos = y - tooltipMarginBottom
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
        val textPaint = Paint().apply {
            isAntiAlias = true
            textSize = toolTextSize
            color = Color.parseColor("#121212")
        }
        val bounds = Rect()
        textPaint.getTextBounds(message, 0, message!!.length, bounds)


        val paintTooltip = Paint().apply {
            isAntiAlias = true
            color = Color.parseColor("#F8FFEC")
            setShadowLayer(
                Utils.dpToPx(context, 4F), 0F,
                Utils.dpToPx(context, 4F), Color.parseColor("#29000000")
            )
        }
        val path = Path()
        path.moveTo(xPos - triangleSize, yPos - triangleSize)
        path.lineTo(xPos, yPos)
        path.lineTo(xPos + triangleSize, yPos - triangleSize)

        val topPos = yPos - triangleSize - bounds.height().toFloat() - tooltipPaddingVertical * 2
        val righPos = xPos + bounds.width().toFloat() * 2 / 3 + tooltipPaddingHorizontal
        path.addRoundRect(
            xPos - bounds.width().toFloat() / 3 - tooltipPaddingHorizontal,
            topPos,
            righPos,
            yPos - triangleSize,
            10F,
            10F,
            Path.Direction.CW
        )

        if (topPos < 0) {
            canvas.translate(0F, 0F - topPos)
        }

        canvas.drawPath(path, paintTooltip)

        canvas.drawText(
            message!!,
            xPos - bounds.width().toFloat() / 3,
            yPos - triangleSize - tooltipPaddingVertical,
            textPaint
        )

    }

    companion object {
        const val DEFAULT_PADDING_VERTICAL = 4F
        const val DEFAULT_PADDING_HORIZONTAL = 8F
        const val DEFAULT_TEXT_SIZE = 12F
        const val DEFAULT_TOOLTIP_MARGIN_BOTTOM = 4F
        const val DEFAULT_TRIANGLE_SIZE = 5F
    }

}