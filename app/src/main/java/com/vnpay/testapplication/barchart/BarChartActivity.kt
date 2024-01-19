package com.vnpay.testapplication.barchart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vnpay.testapplication.R

class BarChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)

        val barChart = findViewById<FinanceBarChart>(R.id.barChart)
        barChart.submitData(
            listOf(
                FinanceBarChart.Data(225F, 450F, "Jan"),
                FinanceBarChart.Data(150F, 300F, "Feb"),
                FinanceBarChart.Data(200F, 500F, "Mar"),
                FinanceBarChart.Data(300F, 600F, "Apr"),
                FinanceBarChart.Data(100F, 100F, "May"),
                FinanceBarChart.Data(100F, 250F, "Jun"),
                FinanceBarChart.Data(550F, 800F, "Jul"),
                FinanceBarChart.Data(850F, 900F, "Aug"),
                FinanceBarChart.Data(600F, 500F, "Sept"),
                FinanceBarChart.Data(200F, 600F, "Oct"),
                FinanceBarChart.Data(300F, 800F, "Nov"),
                FinanceBarChart.Data(100F, 700F, "Dec"),
            )
        )
        barChart.setWillDrawLine(true)
        barChart.build()
    }
}