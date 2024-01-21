package com.vnpay.testapplication.barchart.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class LineContainer(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private val listPercentage = mutableListOf<Float>()

    var barWidth: Float = 0F

    var linePaint: Paint? = null
    var circlePaint: Paint? = null
    var strokePaint: Paint? = null

    init {
        setWillNotDraw(false)
    }

    fun setListData(list: List<Float>) {
        this.listPercentage.clear()
        listPercentage.addAll(list)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawLine(it)
            drawDots(it)
        }
    }

    private fun calcYPos(percentage: Float): Float {
        var yPos = height.toFloat() * (1F - percentage) + barWidth / 2
        if (yPos >= height.toFloat() - barWidth / 2) {
            yPos = height.toFloat() - barWidth / 2
        }
        return yPos
    }

    private fun calcXPos(index: Int): Float {
        if (index == 0) return barWidth / 2

        val spaceBetween =
            (width.toFloat() - listPercentage.size * barWidth) / (listPercentage.size - 1)
        return (spaceBetween + barWidth) * index + barWidth / 2
    }

    private fun drawLine(canvas: Canvas) {
        listPercentage.forEachIndexed { index, data ->
            if (index == listPercentage.size - 1) return@forEachIndexed

            canvas.drawLine(
                calcXPos(index),
                calcYPos(data),
                calcXPos(index + 1),
                calcYPos(listPercentage[index + 1]),
                linePaint!!
            )
        }
    }

    private fun drawDots(canvas: Canvas) {
        listPercentage.forEachIndexed { index, percentage ->
            val y = calcYPos(percentage)
            val x = calcXPos(index)

            canvas.drawCircle(x, y, barWidth / 4, strokePaint!!)
            canvas.drawCircle(x, y, barWidth / 4, circlePaint!!)
        }
    }

}