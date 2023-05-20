package com.tmdb.data.source.local.impl.realm.mapping

import com.tmdb.data.db.realm.movie.MovieEntity
import com.tmdb.data.model.movie.MovieDataModel

typealias MovieEntityToDataModelMapper = (input: MovieEntity) -> MovieDataModel

fun movieEntityToDataModelMapperImpl(input: MovieEntity): MovieDataModel =
    MovieDataModel(
        id = input.id,
        title = input.title,
        voteAverage = input.voteAverage,
        releaseDate = input.releaseDate,
        posterUrl = input.posterUrl
    )
