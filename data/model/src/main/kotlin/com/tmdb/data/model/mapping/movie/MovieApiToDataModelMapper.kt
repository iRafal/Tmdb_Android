package com.tmdb.data.model.mapping.movie

import com.tmdb.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.api.model.movie.Movie
import com.tmdb.data.model.movie.MovieDataModel

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
