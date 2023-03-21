package com.tmdb.data.source.local.impl.movie.data.mapping

import com.tmdb.data.db.room.movie.MovieEntity
import com.tmdb.data.model.movie.MovieDataModel

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