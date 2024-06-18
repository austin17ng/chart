package com.vnpay.testapplication.tooltip

import android.graphics.Rect
import android.view.View


fun locateView(v: View?): Rect? {
    val loc_int = IntArray(2)
    if (v == null) return null
    try {
        v.getLocationOnScreen(loc_int)
    } catch (npe: NullPointerException) {
        //Happens when the view doesn't exist on screen anymore.
        return null
    }
    val location = Rect()
    location.left = loc_int[0]
    location.top = loc_int[1]
    location.right = location.left + v.width
    location.bottom = location.top + v.height
    return location
}