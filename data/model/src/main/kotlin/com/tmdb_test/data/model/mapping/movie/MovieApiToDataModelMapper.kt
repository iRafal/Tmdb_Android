package com.tmdb_test.data.model.mapping.movie

import com.tmdb_test.api.config.url.image.contract.ImageUrlProvider
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.data.model.MovieDataModel

typealias MovieApiToDataModelMapper = (input: Movie) -> MovieDataModel

fun movieApiToDataModelMapperImpl(imageUrlProvider: ImageUrlProvider): MovieApiToDataModelMapper = { input ->
        MovieDataModel(
            id = input.id,
            title = input.title,
            voteAverage = input.voteAverage,
            releaseDate = input.releaseDate,
            posterUrl = input.posterPath?.let { imageUrlProvider.posterUrl(it) }
        )
    }