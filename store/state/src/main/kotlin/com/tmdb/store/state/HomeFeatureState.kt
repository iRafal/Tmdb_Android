package com.tmdb.store.state

import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.state.DataState

data class HomeFeatureState(
    val isFullReload: Boolean = false,
    val nowPlayingMovies: MoviesGroup = MoviesGroup.INITIAL,
    val popularMovies: MoviesGroup = MoviesGroup.INITIAL,
    val topRatedMovies: MoviesGroup = MoviesGroup.INITIAL,
    val upcomingMovies: MoviesGroup = MoviesGroup.INITIAL
) {
    val copyAsAllLoading: HomeFeatureState
        get() = this.copy(
            nowPlayingMovies = this.nowPlayingMovies.copyAsLoading,
            popularMovies = this.popularMovies.copyAsLoading,
            topRatedMovies = this.topRatedMovies.copyAsLoading,
            upcomingMovies = this.upcomingMovies.copyAsLoading
        )

    val copyAsFullReload: HomeFeatureState
        get() = this.copy(isFullReload = true)

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
