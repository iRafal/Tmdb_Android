package com.tmdb_test.store.state.home

import com.tmdb_test.data.source.model.MovieDataModel
import com.tmdb_test.store.state.mapping.DataToFeatureStateMapper

typealias MoviesDataToFeatureStateMapper = DataToFeatureStateMapper<List<MovieDataModel>>