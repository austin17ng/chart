package com.vnpay.testapplication

import android.content.Context

object Utils {
    fun dpToPx(context: Context, dp: Float): Float {
        val density = context.resources.displayMetrics.density
        return dp * density
    }
}