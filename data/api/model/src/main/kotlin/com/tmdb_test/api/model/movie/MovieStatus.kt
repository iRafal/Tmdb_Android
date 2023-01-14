package com.tmdb_test.api.model.movie

import com.tmdb_test.api.model.util.serializer.MovieStatusSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MovieStatusSerializer::class)
enum class MovieStatus {
     RUMORED,
     PLANNED,
     IN_PRODUCTION,
     POST_PRODUCTION,
     RELEASED,
     CANCELED
}

