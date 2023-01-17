package com.tmdb_test.data.source.local.impl_realm.mapping

import com.tmdb_test.data.db.realm.movie.MovieEntity
import com.tmdb_test.data.model.movie.MovieDataModel

typealias MovieEntityToDataModelMapper = (input: MovieEntity) -> MovieDataModel

fun movieEntityToDataModelMapperImpl(input: MovieEntity): MovieDataModel =
    MovieDataModel(
        id = input.id,
        title = input.title,
        voteAverage = input.voteAverage,
        releaseDate = input.releaseDate,
        posterUrl = input.posterUrl,
    )