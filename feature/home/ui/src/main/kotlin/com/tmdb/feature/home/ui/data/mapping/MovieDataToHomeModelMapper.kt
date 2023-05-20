package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.feature.home.ui.data.model.HomeUiData

typealias MovieDataToHomeModelMapper = (input: MovieDataModel) -> HomeUiData.Movie

fun movieDataToHomeModelMapperImpl(): MovieDataToHomeModelMapper = { input ->
    HomeUiData.Movie(
        id = checkNotNull(input.id),
        title = checkNotNull(input.title),
        averageVote = input.voteAverage ?: 0.0,
        releaseDate = input.releaseDate,
        posterUrl = input.posterUrl
    )
}