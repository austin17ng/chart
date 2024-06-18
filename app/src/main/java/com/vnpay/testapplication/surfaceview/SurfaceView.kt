package com.vnpay.testapplication.surfaceview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class SurfaceView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var style: Style? = null
    interface Style {
        fun setNormal() {}
        fun set1() {}
        fun set2() {}
    }

    fun setStyle(style: Style) {
        this.style = style
    }

    fun draw() {
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }


}