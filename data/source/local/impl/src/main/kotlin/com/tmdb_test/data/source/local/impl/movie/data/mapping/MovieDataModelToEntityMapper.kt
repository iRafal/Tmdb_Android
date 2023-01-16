package com.tmdb_test.data.source.local.impl.movie.data.mapping

import com.tmdb_test.data.db.room.movie.MovieEntity
import com.tmdb_test.data.model.movie.MovieDataModel

typealias MovieDataModelToEntityMapper = (input: MovieDataModel) -> MovieEntity

fun movieDataModelToEntityMapperImpl(
    input: MovieDataModel,
): MovieEntity = MovieEntity(
    id = input.id,
    title = input.title,
    voteAverage = input.voteAverage,
    releaseDate = input.releaseDate,
    posterUrl = input.posterUrl,
)