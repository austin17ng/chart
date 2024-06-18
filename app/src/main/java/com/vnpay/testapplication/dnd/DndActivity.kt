package com.vnpay.testapplication.dnd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.View.DRAG_FLAG_OPAQUE
import android.widget.Button
import com.vnpay.testapplication.R

class DndActivity : AppCompatActivity() {
    private lateinit var btn: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd)

        btn = findViewById(R.id.button)

        btn.animate().scaleX(2f)
            .scaleY(2f)
    }
}