package com.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.tmdb.ui.app.navigation.AppNavigation
import com.tmdb.ui.core.theme.TmdbTheme
import com.tmdb.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { SetContent() }
    }

    @Composable
    private fun SetContent() {
        TmdbTheme { AppNavigation { finish() } }
    }
}
