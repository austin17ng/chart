package com.vnpay.testapplication.barchart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vnpay.testapplication.R
import com.vnpay.testapplication.barchart.components.BarChart

class BarChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)

        val barChart = findViewById<BarChart>(R.id.barChart)
        barChart.submitData(
            listOf(
                BarChart.Data(225000000F, 450000000F, "Jan"),
                BarChart.Data(150000000F, 300000000F, "Feb"),
                BarChart.Data(200000000F, 500000000F, "Mar"),
                BarChart.Data(300000000F, 600000000F, "Apr"),
                BarChart.Data(10000000F, 100000000F, "May"),
                BarChart.Data(100000000F, 250000000F, "Jun"),
                BarChart.Data(550000000F, 800000000F, "Jul"),
                BarChart.Data(850000000F, 900000000F, "Aug"),
                BarChart.Data(600000000F, 500000000F, "Sept"),
                BarChart.Data(200000000F, 600000000F, "Oct"),
                BarChart.Data(300000000F, 800000000F, "Nov"),
                BarChart.Data(100000000F, 700000000F, "Dec"),
            )
        )
        barChart.willDrawLine = true
        barChart.build()
    }
}