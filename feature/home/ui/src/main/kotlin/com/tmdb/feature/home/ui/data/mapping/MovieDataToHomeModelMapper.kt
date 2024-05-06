package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.data.model.MovieDataModel
import com.tmdb.feature.home.ui.data.model.Movie
import javax.inject.Inject

interface MovieDataToHomeModelMapper {
    fun map(input: MovieDataModel): Movie
}

class MovieDataToHomeModelMapperImpl @Inject constructor() : MovieDataToHomeModelMapper {
    override fun map(input: MovieDataModel): Movie {
        return Movie(
            id = checkNotNull(input.id),
            title = checkNotNull(input.title),
            averageVote = input.voteAverage ?: 0.0,
            releaseDate = input.releaseDate,
            posterUrl = input.posterUrl
        )
    }
}
