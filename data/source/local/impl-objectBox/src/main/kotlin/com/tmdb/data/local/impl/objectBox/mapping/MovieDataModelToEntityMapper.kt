package com.tmdb.data.local.impl.objectBox.mapping

import com.tmdb.data.db.objectBox.movie.MovieEntity
import com.tmdb.data.model.movie.MovieDataModel

typealias MovieDataModelToEntityMapper = (input: MovieDataModel) -> MovieEntity

fun movieDataModelToEntityMapperImpl(
    input: MovieDataModel
): MovieEntity = MovieEntity(
    movieId = input.id,
    title = input.title,
    voteAverage = input.voteAverage,
    releaseDate = input.releaseDate,
    posterUrl = input.posterUrl
)
