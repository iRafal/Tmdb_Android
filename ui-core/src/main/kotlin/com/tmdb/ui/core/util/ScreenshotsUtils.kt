package com.tmdb.ui.core.util

import android.app.Activity
import android.os.Build
import android.view.WindowManager

// INFO: https://proandroiddev.com/multitasking-intrusion-and-preventing-screenshots-in-android-app-15bd8757c24d

fun Activity.disableScreenshots() {
    window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        setRecentsScreenshotEnabled(false)
    }
}

fun Activity.enableScreenshots() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        setRecentsScreenshotEnabled(true)
    }
}
