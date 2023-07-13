package com.tmdb.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.tmdb.ui.core.theme.TmdbTheme
import com.tmdb.ui.navigation.AppNavigation
import com.tmdb.util.appComponent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationContext.appComponent.inject(this)
        setContent { SetContent() }
    }

    @Composable
    private fun SetContent() {
        TmdbTheme { AppNavigation(onClose = { finish() }) }
    }
}