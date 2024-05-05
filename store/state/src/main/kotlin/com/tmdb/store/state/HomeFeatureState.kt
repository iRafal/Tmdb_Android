package com.tmdb.store.state

import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState

data class HomeFeatureState(
    val nowPlayingMovies: MoviesGroup = MoviesGroup.INITIAL,
    val nowPopularMovies: MoviesGroup = MoviesGroup.INITIAL,
    val topRatedMovies: MoviesGroup = MoviesGroup.INITIAL,
    val upcomingMovies: MoviesGroup = MoviesGroup.INITIAL
) {
    val copyAsAllLoading: HomeFeatureState
        get() = this.copy(
            nowPlayingMovies = this.nowPlayingMovies.copyAsLoading,
            nowPopularMovies = this.nowPopularMovies.copyAsLoading,
            topRatedMovies = this.topRatedMovies.copyAsLoading,
            upcomingMovies = this.upcomingMovies.copyAsLoading
        )

    data class MoviesGroup(
        val isLoading: Boolean = false,
        val movies: DataState<List<MovieDataModel>>? = null
    ) {
        val copyAsLoading: MoviesGroup
            get() = this.copy(isLoading = true)

        companion object {
            val INITIAL = MoviesGroup()
        }
    }

    companion object {
        val INITIAL = HomeFeatureState()
    }
}
