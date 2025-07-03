package com.tmdb.ui.core.compose.navigation.model

import androidx.annotation.Keep
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
sealed interface NavigationRouteNew {
    @Keep
    @Serializable
    data object Home : NavigationRouteNew, NavKey

    @Keep
    @Serializable
    data class MovieDetails(@SerialName("movieId") val movieId: String): NavigationRouteNew, NavKey
}
