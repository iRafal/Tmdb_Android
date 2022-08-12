package com.tmdb_test.data.api.model.genre

import com.tmdb_test.data.api.model.genre.Genre
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class GenresList(@SerialName("genres") val genres: List<Genre> = listOf())
