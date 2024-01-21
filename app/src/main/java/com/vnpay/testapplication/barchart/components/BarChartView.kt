package com.vnpay.testapplication.barchart.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
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

class BarChartView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    data class Data(val expense: Float, val income: Float, val time: String)

    private val listData = mutableListOf<Data>()
    private val listBarView = mutableListOf<BarView>()
    val barContainer: LinearLayout = LinearLayout(context)
    val lineContainer: LineContainer = LineContainer(context, attrs)
    val tooltipContainer: TooltipContainer = TooltipContainer(context, attrs)
    val paintTextTimeLine = Paint().apply {
        textSize = Utils.spToPx(context, 10F)
        isAntiAlias = true
    }

    //bar
    lateinit var barNormalPaint: Paint
    var barActivePaint: Paint? = null
    var barOverPaint: Paint? = null
    lateinit var barBgPaint: Paint
    var barShadowPaint: Paint? = null

    //line
    val linePaint = Paint()
    val lineCirclePaint = Paint()
    val lineStrokePaint = Paint()


    //attrs
    var horizontalPadding = Utils.dpToPx(context, 16F)
    var barHeight = Utils.dpToPx(context, 120F)
    var barWidth = Utils.dpToPx(context, 8F)
    var timelineMarginTop = Utils.dpToPx(context, 5F)

    var type: TYPE = TYPE.SINGLE_TOOL_TIP
    var enableDefaultStyle: Boolean = true

    init {
        setWillNotDraw(false)
        clipChildren = false
    }

    fun submitData(data: List<Data>) {
        this.listData.clear()
        this.listData.addAll(data)
    }

    fun build() {
        //set style
        if (enableDefaultStyle) {
            setDefaultStyle()
        }

        //ad views
        this.removeAllViews()
        addBarContainer()
        if (type == TYPE.LINE) {
            addLineContainer()
        }
        if (type == TYPE.SINGLE_TOOL_TIP) {
            addTooltipContainer()
        }
        addBars()

        //calc data
        calcBarData()

        invalidate()
    }

    private fun setDefaultStyle() {
        setDefaultStyleThemeLight()
    }

    private fun setDefaultStyleThemeLight() {
        when (type) {
            TYPE.SINGLE_TOOL_TIP -> {
                horizontalPadding = Utils.dpToPx(context, 24F)
                barHeight = Utils.dpToPx(context, 110F)
                barWidth = Utils.dpToPx(context, 6F)
                timelineMarginTop = Utils.dpToPx(context, 12F)

                //timeline
                paintTextTimeLine.apply {
                    textSize = Utils.spToPx(context, 14F)
                    color = Color.parseColor("#7E7E7E")
                }

                //tooltip
                tooltipContainer.apply {
                    tooltipPaddingVertical = Utils.dpToPx(context, 4F)
                    tooltipPaddingHorizontal = Utils.dpToPx(context, 8F)
                    tooltipMarginBottom = Utils.dpToPx(context, 4F)
                    triangleHeight = Utils.dpToPx(context, 5F)

                    textPaint.apply {
                        textSize = Utils.spToPx(context, 12F)
                        color = Color.parseColor("#121212")
                    }

                    paintTooltip.apply {
                        color = Color.parseColor("#F8FFEC")
                        setShadowLayer(
                            Utils.dpToPx(context, 4F), 0F,
                            Utils.dpToPx(context, 4F), Color.parseColor("#29000000")
                        )
                    }
                }

                //bar
                barNormalPaint = VerticalGradientPaint(
                    // design đang để góc gradient xiên
                    Color.parseColor("#1D8321"),
                    Color.parseColor("#A8E751")
                ).apply {
                    isAntiAlias = true
                }
                barActivePaint = Paint().apply {
                    color = Color.parseColor("#A1C038")
                    isAntiAlias = true
                }
                barBgPaint = VerticalGradientPaint(
                    Color.parseColor("#33A1C038"),
                    Color.parseColor("#00A1C038")
                ).apply {
                    isAntiAlias = true
                }
                barShadowPaint = Paint().apply {
                    isAntiAlias = true
                    color = Color.parseColor("#E9F3D7")
                }
                barOverPaint = Paint().apply {
                    isAntiAlias = true
                    color = Color.parseColor(("#DE3E37"))
                }
            }

            TYPE.OVERSPENDING -> {
                horizontalPadding = Utils.dpToPx(context, 24F)
                barHeight = Utils.dpToPx(context, 110F)
                barWidth = Utils.dpToPx(context, 6F)
                timelineMarginTop = Utils.dpToPx(context, 12F)

                //timeline
                paintTextTimeLine.apply {
                    textSize = Utils.spToPx(context, 14F)
                    color = Color.parseColor("#7E7E7E")
                }

                //bar
                barNormalPaint = VerticalGradientPaint(
                    Color.parseColor("#1D8321"),
                    Color.parseColor("#A8E751")
                ).apply {
                    isAntiAlias = true
                }
                barBgPaint = Paint().apply {
                    isAntiAlias = true
                    color = Color.parseColor("#33A1C038")
                }
                barShadowPaint = Paint().apply {
                    isAntiAlias = true
                    color = Color.parseColor("#E9F3D7")
                }
                barOverPaint = Paint().apply {
                    isAntiAlias = true
                    color = Color.parseColor(("#DE3E37"))
                }

            }

            TYPE.LINE -> {
                horizontalPadding = Utils.dpToPx(context, 16F)
                barHeight = Utils.dpToPx(context, 100F)
                barWidth = Utils.dpToPx(context, 9F)
                timelineMarginTop = Utils.dpToPx(context, 8F)

                //timeline
                paintTextTimeLine.apply {
                    textSize = Utils.spToPx(context, 14F)
                    color = Color.parseColor("#7E7E7E")
                }

                //bar
                barNormalPaint = Paint().apply {
                    color = Color.parseColor("#A1C038")
                    isAntiAlias = true
                }
                barBgPaint = VerticalGradientPaint(
                    Color.parseColor("#33A1C038"),
                    Color.parseColor("#00A1C038")
                ).apply {
                    isAntiAlias = true
                }

                //line
                linePaint.apply {
                    isAntiAlias = true
                    color = Color.parseColor("#F58F20")
                    strokeWidth = Utils.dpToPx(context, 2F)
                }
                lineCirclePaint.apply {
                    isAntiAlias = true
                    color = Color.parseColor("#F58F20")
                }
                lineStrokePaint.apply {
                    isAntiAlias = true
                    color = Color.parseColor("#FFFFFF")
                    style = Paint.Style.STROKE
                    strokeWidth = Utils.dpToPx(context, Utils.dpToPx(context, 1F))
                }
            }
        }
    }

    private fun setDefaultStyleThemeVIP() {

    }

    private fun setDefaultStyleThemYoung() {

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (type != TYPE.SINGLE_TOOL_TIP) return true

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                listBarView.forEach { barView ->
                    //make clickable span larger a little bit (+- barWidth/2)
                    if (barView.x + horizontalPadding - barWidth/2 <= event.x
                        && event.x <= barView.x + barView.width + horizontalPadding + barWidth
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
            NumberFormat.getNumberInstance(Locale.US).format(listData[selectedIndex].income)
        )

        listBarView.forEachIndexed { index, barView ->
            barView.isClicked = index == selectedIndex
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
        when (type) {
            TYPE.SINGLE_TOOL_TIP -> {
                val maxIncome = this.listData.maxOf { it.income }
                listData.forEachIndexed { index, data ->
                    listBarView[index].percentage = data.income / maxIncome
                }
            }

            TYPE.OVERSPENDING -> {
                listData.forEachIndexed { index, data ->
                    if (data.expense < data.income) {
                        listBarView[index].percentage = data.expense / data.income
                    } else {
                        val percentage = data.expense / (data.income + data.expense)
                        listBarView[index].percentage = percentage
                        listBarView[index].overPercentage = 1 - percentage
                    }
                }
            }

            TYPE.LINE -> {
                val maxIncome = this.listData.maxOf { it.income }
                listData.forEachIndexed { index, data ->
                    listBarView[index].percentage = data.income / maxIncome
                }

                lineContainer.setListData(
                    listData.map { it.expense / maxIncome }
                )
            }
        }

    }


    private fun drawTimeLine(canvas: Canvas) {
        listBarView.forEachIndexed { index, barView ->
            val time = listData[index].time
            val barCenterX = barView.x + barView.width / 2 + horizontalPadding
            canvas.drawMultilineTextCenter(
                time,
                barCenterX,
                barHeight + timelineMarginTop,
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
                    (getTimeLineHeight() + timelineMarginTop).toInt()
                )
            }
        barContainer.clipChildren = false
        this.addView(barContainer)
    }

    private fun addLineContainer() {
        lineContainer.layoutParams =
            LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                setMargins(
                    horizontalPadding.toInt(),
                    0,
                    horizontalPadding.toInt(),
                    (timelineMarginTop + getTimeLineHeight()).toInt()
                )
            }
        lineContainer.barWidth = this.barWidth
        lineContainer.linePaint = this.linePaint
        lineContainer.circlePaint = this.lineCirclePaint
        lineContainer.strokePaint = this.lineStrokePaint
        this.addView(lineContainer)
    }

    private fun addTooltipContainer() {
        tooltipContainer.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                setMargins(
                    horizontalPadding.toInt(),
                    0,
                    horizontalPadding.toInt(),
                    (timelineMarginTop + getTimeLineHeight()).toInt()
                )
            }
        tooltipContainer.spaceToBarChart = horizontalPadding
        this.addView(tooltipContainer)
    }

    private fun getTimeLineHeight(): Float {
        val maxLine = listData.maxOfOrNull { it.time.split("\n").size } ?: 0
        val fontHeight =
            paintTextTimeLine.fontMetrics.descent - paintTextTimeLine.fontMetrics.ascent
        return fontHeight * maxLine
    }

    private fun addBars() {
        listBarView.clear()
        listData.forEachIndexed { index, data ->
            val barView = BarView(context).apply {
                layoutParams = LayoutParams(barWidth.toInt(), LayoutParams.MATCH_PARENT)
                barNormalPaint = this@BarChartView.barNormalPaint
                barActivePaint = this@BarChartView.barActivePaint
                barOverPaint = this@BarChartView.barOverPaint
                bgPaint = this@BarChartView.barBgPaint
                shadowPaint = this@BarChartView.barShadowPaint
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

    enum class TYPE {
        SINGLE_TOOL_TIP, // chỉ có 1 loại thu hoặc chi, có show tooltip
        LINE, // thu chi, có show line
        OVERSPENDING // thu chi, có show overspending
    }
}