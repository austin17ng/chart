package com.vnpay.testapplication.barchart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Space
import com.vnpay.testapplication.Utils

class FinanceBarChart(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    data class Data(val expense: Float, val income: Float, val time: String)

    private val listData = mutableListOf<Data>()
    private val barWrapperView: LinearLayout = LinearLayout(context)
    private val lineFrame: LineView = LineView(context, attrs)
    private val tooltipView: TooltipView = TooltipView(context, attrs)
    private val listBarView = mutableListOf<BarView>()
    private var willDrawLine: Boolean = false


    //attrs
    private var horizontalPadding = 0F
    private var barHeight = 0F
    private var barWidth = 0F
    private var timelineMarginTop = 0F
    private var timelineHeight = 0F


    init {
        horizontalPadding = Utils.dpToPx(context, DEFAULT_HORIZONTAL_PADDING.toFloat())
        barHeight = Utils.dpToPx(context, DEFAULT_BAR_HEIGHT.toFloat())
        barWidth = Utils.dpToPx(context, DEFAULT_BAR_WIDTH.toFloat())
        timelineMarginTop = Utils.dpToPx(context, DEFAULT_TIMELINE_MARGIN_TOP.toFloat())
        timelineHeight = Utils.dpToPx(context, DEFAULT_TIMELINE_HEIGHT.toFloat())

        setWillNotDraw(false)
        clipChildren = false

        barWrapperView.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, barHeight.toInt()).apply {
                setMargins(
                    horizontalPadding.toInt(),
                    0,
                    horizontalPadding.toInt(),
                    timelineHeight.toInt()
                )
            }
        barWrapperView.clipChildren = false
        this.addView(barWrapperView)

        lineFrame.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                setMargins(
                    horizontalPadding.toInt(),
                    0,
                    horizontalPadding.toInt(),
                    timelineHeight.toInt()
                )
            }
        this.addView(lineFrame)

        tooltipView.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                setMargins(
                    horizontalPadding.toInt(),
                    0,
                    horizontalPadding.toInt(),
                    timelineHeight.toInt()
                )
            }
        this.addView(tooltipView)
    }

    fun submitData(data: List<Data>) {
        this.listData.clear()
        this.listData.addAll(data)
    }

    fun setWillDrawLine(drawLine: Boolean) {
        this.willDrawLine = drawLine
    }

    fun build() {
        addBars()
        calcBarData()
        if (willDrawLine) {
            lineFrame.setBarWidth(barWidth)
            lineFrame.setListData(listData)
        }
        lineFrame.invalidate()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                listBarView.forEach { barView ->
                    if (barView.x + horizontalPadding <= event.x
                        && event.x <= barView.x + barView.width + horizontalPadding
                        && barView.y <= event.y
                        && event.y <= barView.y + barView.height
                    ) {
                        onBarClicked(barView)
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun onBarClicked(barView: BarView) {

        val index = listBarView.indexOf(barView)
        tooltipView.showTooltip(
            barView.x + barView.width/2,
            barView.getBarTopPos(),
            barView.income.toString()
        )
        Log.d("barchart", index.toString())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        drawTimeLine(canvas)
    }

    private fun calcBarData() {
        val maxIncome = this.listData.map { it.income }.max()
        listData.forEachIndexed { index, data ->
            listBarView[index].income = data.income
            listBarView[index].expense = data.expense
            listBarView[index].maxIncome = maxIncome
            listBarView[index].incomePercent = data.income / maxIncome
            listBarView[index].expensePercent = data.expense / data.income
        }
    }


    private fun drawTimeLine(canvas: Canvas) {
        val paint = Paint().apply {
            textSize = 40f
        }
        val fm: Paint.FontMetrics = paint.fontMetrics
        val textHeight = fm.descent - fm.ascent

        listBarView.forEachIndexed { index, barView ->
            val time = listData[index].time
            val barCenterX = barView.x + barView.width / 2
            val textWidth = paint.measureText(time)
            canvas.drawText(
                time,
                barCenterX - textWidth / 2 + horizontalPadding,
                barHeight + timelineMarginTop + textHeight / 2,
                paint
            )
        }
    }

    private fun addBars() {
        listBarView.clear()
        listData.forEachIndexed { index, data ->
            val barView = BarView(context).apply {
                layoutParams = LayoutParams(barWidth.toInt(), LayoutParams.MATCH_PARENT)
            }
            barWrapperView.addView(barView)
            listBarView.add(barView)


            if (index == listData.size - 1) return@forEachIndexed
            val space = Space(context)
            space.layoutParams =
                LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
            barWrapperView.addView(space)
        }
    }


    companion object {
        const val DEFAULT_HORIZONTAL_PADDING = 16
        const val DEFAULT_BAR_HEIGHT = 120
        const val DEFAULT_BAR_WIDTH = 8
        const val DEFAULT_TIMELINE_MARGIN_TOP = 5
        const val DEFAULT_TIMELINE_HEIGHT = 32

        const val TYPE_0 = 0 // thu or chi
        const val TYPE_1 = 1 // thu and chi
        const val TYPE_2 = 2 // thu chi line
    }

}