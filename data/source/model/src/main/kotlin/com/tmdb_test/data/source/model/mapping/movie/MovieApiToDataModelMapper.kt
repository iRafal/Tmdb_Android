package com.tmdb_test.data.source.model.mapping.movie

import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.data.source.model.MovieDataModel

typealias MovieApiToDataModelMapper = (input: Movie) -> MovieDataModel

fun movieApiToDataModelMapperImpl(input: Movie): MovieDataModel {
    return MovieDataModel(
        id = input.id,
        title = input.title,
        voteAverage = input.voteAverage,
        releaseDate = input.releaseDate,
        posterPath = input.posterPath
    )
}