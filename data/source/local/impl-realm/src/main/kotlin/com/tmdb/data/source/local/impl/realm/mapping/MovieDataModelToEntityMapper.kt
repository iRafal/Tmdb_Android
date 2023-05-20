package com.tmdb.data.source.local.impl.realm.mapping

import com.tmdb.data.db.realm.movie.MovieEntity
import com.tmdb.data.model.movie.MovieDataModel

typealias MovieDataModelToEntityMapper = (input: MovieDataModel) -> MovieEntity

fun movieDataModelToEntityMapperImpl(
    input: MovieDataModel
): MovieEntity = MovieEntity(
    id = input.id,
    title = input.title,
    voteAverage = input.voteAverage,
    posterUrl = input.posterUrl
).apply {
    releaseDate = input.releaseDate
}
