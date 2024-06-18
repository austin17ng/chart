package com.vnpay.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.vnpay.testapplication.barchart.BarChartActivity
import com.vnpay.testapplication.dnd.DndActivity
import com.vnpay.testapplication.expandable.ExpanableActivity
import com.vnpay.testapplication.lio.LioActivity
import com.vnpay.testapplication.paging.PagingActivity
import com.vnpay.testapplication.tabs.TabsActivity
import com.vnpay.testapplication.tooltip.TooltipActivity

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

        findViewById<Button>(R.id.btnTooltip).setOnClickListener {
            startActivity(Intent(this, TooltipActivity::class.java))
        }

        findViewById<Button>(R.id.btnDnD).setOnClickListener {
            startActivity(Intent(this, DndActivity::class.java))
        }

        findViewById<Button>(R.id.btnLio).setOnClickListener {
            startActivity(Intent(this, LioActivity::class.java))
        }

        findViewById<Button>(R.id.btnTabs).setOnClickListener {
            startActivity(Intent(this, TabsActivity::class.java))
        }

        findViewById<Button>(R.id.btnPaging).setOnClickListener {
            startActivity(Intent(this, PagingActivity::class.java))
        }
    }
}