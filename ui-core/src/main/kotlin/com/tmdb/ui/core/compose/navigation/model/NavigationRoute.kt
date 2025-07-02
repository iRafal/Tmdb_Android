package com.tmdb.ui.core.compose.navigation.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
sealed interface NavigationRoute {
    @Keep
    @Serializable
    data object Home : NavigationRoute

    @Keep
    @Serializable
    data class MovieDetails(val movieId: String)
}
