package com.prmto.poxedex.presentation.util

import android.app.Activity
import android.util.TypedValue
import androidx.annotation.ColorRes

fun Activity.setStatusBarColor(
    @ColorRes colorId: Int
) {
    val color = resources.getColor(colorId, theme)
    window.statusBarColor = color
}

fun Activity.setStatusBarColorToPrimary() {
    val typedValue = TypedValue()
    theme.resolveAttribute(android.R.attr.colorPrimary, typedValue, true)
    val color = typedValue.data
    window.statusBarColor = color
}