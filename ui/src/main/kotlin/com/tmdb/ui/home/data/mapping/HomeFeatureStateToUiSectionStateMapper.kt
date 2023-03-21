package com.tmdb.ui.home.data.mapping

import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.ui.home.data.HomeUiData.Movie
import com.tmdb.ui.core.data.mapping.FeatureToUiStateMapper

typealias HomeFeatureStateToUiSectionStateMapper =
        FeatureToUiStateMapper<List<MovieDataModel>, List<Movie>>