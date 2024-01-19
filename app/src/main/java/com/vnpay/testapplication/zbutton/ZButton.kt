package com.vnpay.testapplication.zbutton

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class ZButton(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    init {
        clipChildren = false
    }
}