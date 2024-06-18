package com.vnpay.testapplication.tooltip

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.vnpay.testapplication.R
import com.vnpay.testapplication.tooltip.ViewTooltip.FadeTooltipAnimation
import com.vnpay.testapplication.tooltip.ViewTooltip.ListenerDisplay
import com.vnpay.testapplication.tooltip.ViewTooltip.ListenerHide


class TooltipActivity : AppCompatActivity() {

    private lateinit var mPopupWindow: PopupWindow

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tooltip)

        val btn = findViewById<Button>(R.id.btnTooltip)

        btn.setOnClickListener {
            ViewTooltip
                .on(btn)
                .position(ViewTooltip.Position.TOP)
                .arrowSourceMargin(0)
                .arrowTargetMargin(0)
                .text("wasssup")
                .clickToHide(true)
                .autoHide(true, 1500)
                .animation(FadeTooltipAnimation(500))
                .onDisplay(ListenerDisplay { Log.d("ViewTooltip", "onDisplay") })
                .onHide(ListenerHide { Log.d("ViewTooltip", "onHide") })
                .show()
        }

        val btnSecond = findViewById<Button>(R.id.btnTooltipSecond)
        btnSecond.tooltipText = "Hello"
//        btnSecond.setOnClickListener {
//            val location = locateView(btnSecond)
//            // Show the popup window at the center of activity's root view
//            mPopupWindow.showAtLocation(
//                this.window.decorView,
//                Gravity.BOTTOM,
//                location!!.left,
//                location.bottom
//            )
//        }

        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // Inflate the custom layout/view
        val customView = inflater.inflate(R.layout.layout_tooltip, null)

        // Initialize a new instance of popup window
        mPopupWindow = PopupWindow(
            customView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // Set some properties for the popup window
        mPopupWindow.isOutsideTouchable = true
        mPopupWindow.isFocusable = true
    }
}