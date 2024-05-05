package com.tmdb.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.tmdb.R
import com.tmdb.ui.core.util.disableScreenshots
import com.tmdb.util.RootUtils
import com.tmdb.util.checkGooglePlayServicesAvailability
import com.tmdb.util.showRootDeviceErrorAlert

/**
 * INFO: need to show custom splash screen in case of showing custom error alert dialog,
 * due to comment https://developer.android.com/develop/ui/views/launch/splash-screen/migrate#branding
 *
 * Comment:
 * "If you need to display a dialog, we recommend displaying it over the subsequent custom splash screen activity
 * or over the main activity after the system splash screen."
 */
@SuppressLint("CustomSplashScreen")
class CustomSplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_splash_activity)
        disableScreenshots()
        checkIllegalDeviceConfig()
    }

    private fun checkIllegalDeviceConfig() {
        if (RootUtils.isRooted) {
            showRootDeviceErrorAlert()
            return
        }

        checkGooglePlayServicesAvailability()
    }
}
