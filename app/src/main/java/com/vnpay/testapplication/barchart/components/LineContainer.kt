package com.vnpay.testapplication.barchart.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import com.vnpay.testapplication.Utils

class LineContainer(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private val listData = mutableListOf<BarChart.Data>()
    var barWidth: Float = 0F

    val paintLine = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#F2570A")
        strokeWidth = Utils.dpToPx(context, 2F)
    }

    val circlePaint = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#F2570A")
    }

    val strokePaint = Paint().apply {
        isAntiAlias = true
        color = Color.parseColor("#B3FFFFFF")
        style = Paint.Style.STROKE
        strokeWidth = Utils.dpToPx(context, Utils.dpToPx(context, 1F))
    }


    init {
        setWillNotDraw(false)
    }

    fun setListData(list: List<BarChart.Data>) {
        this.listData.clear()
        listData.addAll(list)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawLine(it)
            drawDots(it)
        }
    }

    private fun drawLine(canvas: Canvas) {
        val maxIncome = listData.map { it.income }.max()


        var prevX = barWidth / 2
        var prevY = height.toFloat() * (1 - listData[0].expense / maxIncome)
        if (prevY >= height - barWidth / 2) {
            prevY = height - barWidth / 2
        }
        var spaceBetween = (width.toFloat() - listData.size * barWidth) / (listData.size - 1)
        listData.forEachIndexed { index, data ->
            if (index == listData.size - 1) return@forEachIndexed

            val nextX = prevX + spaceBetween + barWidth

            var nextY = height.toFloat() * (1 - listData[index + 1].expense / maxIncome)
            if (nextY >= height - barWidth / 2) {
                nextY = height - barWidth / 2
            }

            canvas.drawLine(prevX, prevY, nextX, nextY, paintLine)

            prevX = nextX
            prevY = nextY
        }
    }

    private fun drawDots(canvas: Canvas) {
        val maxIncome = listData.map { it.income }.max()

        var x = barWidth / 2
        var spaceBetween = (width.toFloat() - listData.size * barWidth) / (listData.size - 1)
        listData.forEachIndexed { index, data ->
            var y = height.toFloat() * (1 - data.expense / maxIncome)
            if (y >= height - barWidth / 2) {
                y = height - barWidth / 2
            }

            canvas.drawCircle(x, y, barWidth / 2, strokePaint)
            canvas.drawCircle(x, y, barWidth / 4, circlePaint)
            x += spaceBetween + barWidth
        }
    }

}