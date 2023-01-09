package com.tmdb_test.ui.home.data.mapping

import com.tmdb_test.api.config.url.image.ImageUrlProvider
import com.tmdb_test.data.source.model.MovieDataModel
import com.tmdb_test.ui.home.data.HomeUiData

typealias MovieDataToHomeModelMapper = (input: com.tmdb_test.data.source.model.MovieDataModel) -> HomeUiData.Movie

fun movieDataToHomeModelMapperImpl(
    imageUrlProvider: ImageUrlProvider
): MovieDataToHomeModelMapper = { input ->
    HomeUiData.Movie(
        id = checkNotNull(input.id),
        title = checkNotNull(input.title),
        averageVote = input.voteAverage ?: 0.0,
        releaseDate = checkNotNull(input.releaseDate),
        posterUrl = input.posterPath?.let { imageUrlProvider.posterUrl(it) }
    )
}

typealias MovieDataItemsToHomeModelMapper = (input: List<com.tmdb_test.data.source.model.MovieDataModel>) -> List<HomeUiData.Movie>

fun movieDataItemsToHomeModelMapperImpl(
    movieDataToHomeModelMapper: MovieDataToHomeModelMapper
): MovieDataItemsToHomeModelMapper = { input -> input.map { movieDataToHomeModelMapper(it) } }