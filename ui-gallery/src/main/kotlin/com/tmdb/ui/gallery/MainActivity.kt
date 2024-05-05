package com.tmdb.ui.gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tmdb.ui.core.compose.theme.TmdbTheme
import com.tmdb.ui.gallery.app.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbTheme {
                AppNavigation()
            }
        }
    }
}