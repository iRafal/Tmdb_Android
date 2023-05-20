package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.feature.home.ui.data.model.HomeUiData.Movie
import com.tmdb.ui.core.data.mapping.FeatureToUiStateMapper

typealias HomeFeatureStateToUiSectionStateMapper =
    FeatureToUiStateMapper<List<MovieDataModel>, List<Movie>>
