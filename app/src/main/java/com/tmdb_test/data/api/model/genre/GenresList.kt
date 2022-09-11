package com.tmdb_test.data.api.model.genre

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresList(@SerialName("genres") val genres: List<Genre> = listOf())
