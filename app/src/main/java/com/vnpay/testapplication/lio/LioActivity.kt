package com.vnpay.testapplication.lio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import com.vnpay.testapplication.R

class LioActivity : AppCompatActivity() {
    private var velocityTracker: VelocityTracker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lio)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                Log.d("xxxxx", "${event.y}")
            }
        }
        return super.onTouchEvent(event)
    }
}