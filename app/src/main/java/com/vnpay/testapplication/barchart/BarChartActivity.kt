package com.vnpay.testapplication.barchart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.vnpay.testapplication.R
import com.vnpay.testapplication.barchart.components.BarChartView
import kotlin.random.Random

class BarChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)

        val barChart = findViewById<BarChartView>(R.id.barChart)
        barChart.type = BarChartView.TYPE.LINE

        when (barChart.type) {
            BarChartView.TYPE.SINGLE_TOOL_TIP -> {
                val list = mutableListOf<BarChartView.BarSingleValue>()
                (1..12).forEach {
                    list.add(
                        BarChartView.BarSingleValue(
                            randomFloat(100F, 1000F),
                            randomString(2)
                        )
                    )
                }
                barChart.submitData(list)
            }

            else -> {
                val list = mutableListOf<BarChartView.BarTwoValues>()
                (1..12).forEach {
                    val income = randomFloat(100F, 1000F)
                    val expense  = income * randomFloat(0F, 1.2F)
                    list.add(
                        BarChartView.BarTwoValues(
                            expense,
                            income,
                            randomString(2)
                        )
                    )
                }
                barChart.submitData(list)
            }
        }
        barChart.build()

        findViewById<Button>(R.id.btn).setOnClickListener {
            Intent(this, BarChartActivity::class.java).also { startActivity(it) }
            finish()
        }
    }

    fun randomFloat(min: Float, max: Float): Float {
        require(min < max) { "Invalid range: min must be less than max" }

        return Random.nextFloat() * (max - min) + min
    }

    fun randomString(length: Int): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset[Random.nextInt(charset.length)] }
            .joinToString("")
    }
}