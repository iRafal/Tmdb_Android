package com.tmdb

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.metrics.performance.JankStats
import com.tmdb.splash.CustomSplashActivity
import com.tmdb.ui.core.compose.theme.TmdbTheme
import com.tmdb.ui.core.util.disableScreenshots
import com.tmdb.ui.navigation.AppNavigation
import com.tmdb.util.RootUtils
import com.tmdb.util.isGooglePlayServicesAvailable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var janksStats: JankStats? = null

    private val viewModel by viewModels<MainViewModel>()

    private var keepShowingSplash: Boolean by mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.initSplash()
        disableScreenshots()
        checkIllegalDeviceConfig()
        setContent { SetContent() }
    }

    private fun SplashScreen.initSplash() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect { keepShowingSplash = it }
            }
        }
        viewModel.setLoading()
        setKeepOnScreenCondition { keepShowingSplash }
    }

    private fun checkIllegalDeviceConfig() {
        if (RootUtils.isRooted) {
            showCustomSplashScreen()
            return
        }

        if (!isGooglePlayServicesAvailable()) {
            showCustomSplashScreen()
            return
        }

        viewModel.setNotLoading()
    }

    private fun showCustomSplashScreen() {
        startActivity(Intent(this, CustomSplashActivity::class.java))
        finish()
    }

    @Composable
    private fun SetContent() {
        TmdbTheme { AppNavigation(onClose = { finish() }) }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initJankStats()
    }

    private fun initJankStats() {
        val frameListener = JankStats.OnFrameListener { frameData ->
            if (frameData.isJank) frameData.toString()
        }
        janksStats = JankStats.createAndTrack(window, frameListener)
    }

    override fun onResume() {
        super.onResume()
        janksStats?.isTrackingEnabled = true
    }

    override fun onPause() {
        super.onPause()
        janksStats?.isTrackingEnabled = false
    }
}
