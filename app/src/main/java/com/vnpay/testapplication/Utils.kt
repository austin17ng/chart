package com.vnpay.testapplication

import android.content.Context
import android.util.TypedValue

object Utils {
    fun dpToPx(context: Context, dp: Float): Float {
        val density = context.resources.displayMetrics.density
        return dp * density
    }

    fun spToPx(context: Context, sp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics);
    }

    fun getBarChartType(): Int = 0
}