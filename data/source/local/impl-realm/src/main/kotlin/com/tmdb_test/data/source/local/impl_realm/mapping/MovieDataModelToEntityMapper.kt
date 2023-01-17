package com.tmdb_test.data.source.local.impl_realm.mapping

import com.tmdb_test.data.db.realm.movie.MovieEntity
import com.tmdb_test.data.model.movie.MovieDataModel

typealias MovieDataModelToEntityMapper = (input: MovieDataModel) -> MovieEntity

fun movieDataModelToEntityMapperImpl(
    input: MovieDataModel,
): MovieEntity = MovieEntity(
    id = input.id,
    title = input.title,
    voteAverage = input.voteAverage,
    posterUrl = input.posterUrl,
).apply {
    releaseDate = input.releaseDate
}