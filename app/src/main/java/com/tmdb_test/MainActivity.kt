package com.tmdb_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.tmdb_test.ui.app.navigation.AppNavigation
import com.tmdb_test.ui.main.MainViewModel
import com.tmdb_test.ui.theme.Tmdb_TestTheme
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
        Tmdb_TestTheme { AppNavigation { finish() } }
    }
}