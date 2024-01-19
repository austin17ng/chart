package com.vnpay.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.vnpay.testapplication.barchart.BarChartActivity
import com.vnpay.testapplication.expandable.ExpanableActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn1).setOnClickListener {
            startActivity(Intent(this, ExpanableActivity::class.java))
        }

        findViewById<Button>(R.id.btnBarChart).setOnClickListener {
            startActivity(Intent(this, BarChartActivity::class.java))
        }
    }
}