package com.tmdb_test.data.api.model.movie

import com.tmdb_test.data.api.model.util.serializer.MovieStatusSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MovieStatusSerializer::class)
sealed class MovieStatus(val name: String) {
    object Rumored : MovieStatus(RUMORED)
    object Planned : MovieStatus(PLANNED)
    object InProduction : MovieStatus(IN_PRODUCTION)
    object PostProduction : MovieStatus(POST_PRODUCTION)
    object Released : MovieStatus(RELEASED)
    object Canceled : MovieStatus(CANCELED)

    companion object {
        private const val RUMORED = "Rumored"
        private const val PLANNED = "Planned"
        private const val IN_PRODUCTION = "In Production"
        private const val POST_PRODUCTION = "Post Production"
        private const val RELEASED =  "Released"
        private const val CANCELED = "Canceled"

        fun findById(status: String): MovieStatus {
            return findByIdOrNull(status)
                ?: throw IllegalArgumentException("No ${MovieStatus::class.simpleName} with status:$status")
        }

        fun findByIdOrNull(status: String): MovieStatus? {
            return when (status) {
                RUMORED -> Rumored
                PLANNED -> Planned
                IN_PRODUCTION -> InProduction
                POST_PRODUCTION -> PostProduction
                RELEASED -> Released
                CANCELED -> Canceled
                else -> null
            }
        }
    }
}

