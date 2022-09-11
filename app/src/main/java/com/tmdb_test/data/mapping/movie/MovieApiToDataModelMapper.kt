package com.tmdb_test.data.mapping.movie

import com.tmdb_test.data.api.model.movie.Movie
import com.tmdb_test.data.model.MovieDataModel

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