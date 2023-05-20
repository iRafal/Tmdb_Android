package com.tmdb.store.state.home

import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.store.state.mapping.DataToFeatureStateMapper

typealias MoviesDataToFeatureStateMapper = DataToFeatureStateMapper<List<MovieDataModel>>
