package com.vnpay.testapplication.barchart.components

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
import java.text.NumberFormat
import java.util.Locale

class BarChart(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    data class Data(val expense: Float, val income: Float, val time: String)

    private val listData = mutableListOf<Data>()
    private val listBarView = mutableListOf<BarView>()
    val barContainer: LinearLayout = LinearLayout(context)
    val lineContainer: LineContainer = LineContainer(context, attrs)
    val tooltipContainer: TooltipContainer = TooltipContainer(context, attrs)
    var willDrawLine: Boolean = false
    val paintTextTimeLine = Paint().apply {
        textSize = Utils.spToPx(context, 10F)
        isAntiAlias = true
    }


    //attrs
    var horizontalPadding = Utils.dpToPx(context, 16F)
    var barHeight = Utils.dpToPx(context, 120F)
    var timelineMarginTop = Utils.dpToPx(context, 5F)
    var timelineHeight = Utils.dpToPx(context, 32F)
    var barWidth = Utils.dpToPx(context, 8F)


    init {
        setWillNotDraw(false)
        clipChildren = false
    }

    fun submitData(data: List<Data>) {
        this.listData.clear()
        this.listData.addAll(data)
    }

    fun build() {
        this.removeAllViews()
        addBarContainer()
        addLineContainer()
        addTooltipContainer()
        addBars()
        calcBarData()
        if (willDrawLine) {
            lineContainer.barWidth = this.barWidth
            lineContainer.setListData(listData)
        }
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

        val selectedIndex = listBarView.indexOf(barView)
        tooltipContainer.showTooltip(
            barView.x + barView.width / 2,
            barView.getBarTopPos(),
            NumberFormat.getNumberInstance(Locale.US).format(barView.income)
        )

        listBarView.forEachIndexed { index, barView ->
            barView.shouldShowShadow = index == selectedIndex
            barView.invalidate()
        }
        Log.d("barchart", selectedIndex.toString())
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
        val fm: Paint.FontMetrics = paintTextTimeLine.fontMetrics
        val textHeight = fm.descent - fm.ascent

        listBarView.forEachIndexed { index, barView ->
            val time = listData[index].time
            val barCenterX = barView.x + barView.width / 2
            val textWidth = paintTextTimeLine.measureText(time)
            canvas.drawText(
                time,
                barCenterX - textWidth / 2 + horizontalPadding,
                barHeight + timelineMarginTop + textHeight / 2,
                paintTextTimeLine
            )
        }
    }

    private fun addBarContainer() {
        barContainer.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, barHeight.toInt()).apply {
                setMargins(
                    horizontalPadding.toInt(),
                    0,
                    horizontalPadding.toInt(),
                    timelineHeight.toInt()
                )
            }
        barContainer.clipChildren = false
        this.addView(barContainer)
    }

    private fun addLineContainer() {
        lineContainer.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                setMargins(
                    horizontalPadding.toInt(),
                    0,
                    horizontalPadding.toInt(),
                    timelineHeight.toInt()
                )
            }
        this.addView(lineContainer)
    }

    private fun addTooltipContainer() {
        tooltipContainer.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                setMargins(
                    horizontalPadding.toInt(),
                    0,
                    horizontalPadding.toInt(),
                    timelineHeight.toInt()
                )
            }
        tooltipContainer.spaceToBarChart = horizontalPadding
        this.addView(tooltipContainer)
    }

    private fun addBars() {
        listBarView.clear()
        listData.forEachIndexed { index, data ->
            val barView = BarView(context).apply {
                layoutParams = LayoutParams(barWidth.toInt(), LayoutParams.MATCH_PARENT)
            }
            barContainer.addView(barView)
            listBarView.add(barView)


            if (index == listData.size - 1) return@forEachIndexed
            val space = Space(context)
            space.layoutParams =
                LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
            barContainer.addView(space)
        }
    }


    companion object {
        const val TYPE_0 = 0 // thu or chi
        const val TYPE_1 = 1 // thu and chi
        const val TYPE_2 = 2 // thu chi line

        //thu va chi (co line)
        //chi (ngan sach vuot nguong)
        //thu
    }

}