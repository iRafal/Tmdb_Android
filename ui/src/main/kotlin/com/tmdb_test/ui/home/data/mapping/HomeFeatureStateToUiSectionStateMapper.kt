package com.tmdb_test.ui.home.data.mapping

import com.tmdb_test.data.model.movie.MovieDataModel
import com.tmdb_test.ui.home.data.HomeUiData.Movie
import com.tmdb_test.ui.util.data.mapping.FeatureToUiStateMapper

typealias HomeFeatureStateToUiSectionStateMapper =
        FeatureToUiStateMapper<List<MovieDataModel>, List<Movie>>