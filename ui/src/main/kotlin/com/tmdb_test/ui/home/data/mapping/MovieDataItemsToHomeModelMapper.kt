package com.tmdb_test.ui.home.data.mapping

import com.tmdb_test.data.model.MovieDataModel
import com.tmdb_test.ui.home.data.HomeUiData

typealias MovieDataItemsToHomeModelMapper = (input: List<MovieDataModel>) -> List<HomeUiData.Movie>

fun movieDataItemsToHomeModelMapperImpl(
    movieDataToHomeModelMapper: MovieDataToHomeModelMapper
): MovieDataItemsToHomeModelMapper = { input -> input.map { movieDataToHomeModelMapper(it) } }