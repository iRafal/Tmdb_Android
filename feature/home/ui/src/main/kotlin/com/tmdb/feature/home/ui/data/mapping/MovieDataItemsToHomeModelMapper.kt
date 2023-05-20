package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.feature.home.ui.data.model.HomeUiData

typealias MovieDataItemsToHomeModelMapper = (input: List<MovieDataModel>) -> List<HomeUiData.Movie>

fun movieDataItemsToHomeModelMapperImpl(
    movieDataToHomeModelMapper: MovieDataToHomeModelMapper
): MovieDataItemsToHomeModelMapper = { input -> input.map { movieDataToHomeModelMapper(it) } }
