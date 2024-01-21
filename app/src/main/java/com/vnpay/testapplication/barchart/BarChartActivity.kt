package com.vnpay.testapplication.barchart

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.vnpay.testapplication.R
import com.vnpay.testapplication.barchart.components.BarChartView

class BarChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)

        val barChart = findViewById<BarChartView>(R.id.barChart)
        barChart.submitData(
            listOf(
                BarChartView.Data(225000000F, 450000000F, "T1\n2022"),
                BarChartView.Data(150000000F, 300000000F, "Feb"),
                BarChartView.Data(200000000F, 500000000F, "Mar"),
                BarChartView.Data(300000000F, 600000000F, "Apr"),
                BarChartView.Data(100000F, 10000000F, "May"),
                BarChartView.Data(100000000F, 250000000F, "Jun"),
                BarChartView.Data(550000000F, 800000000F, "Jul"),
                BarChartView.Data(850000000F, 900000000F, "Aug"),
                BarChartView.Data(600000000F, 500000000F, "Sept"),
                BarChartView.Data(200000000F, 600000000F, "Oct"),
                BarChartView.Data(300000000F, 800000000F, "Nov"),
                BarChartView.Data(100000000F, 700000000F, "Dec"),
            )
        )
        barChart.type = BarChartView.TYPE.OVERSPENDING
        barChart.build()
    }
}