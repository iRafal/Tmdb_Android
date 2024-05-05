package com.tmdb.store.state

data class AppState(
    val homeState: HomeFeatureState,
    val movieDetailsState: MovieDetailsFeatureState
) {
    companion object {
        val INITIAL = AppState(
            homeState = HomeFeatureState.INITIAL,
            movieDetailsState = MovieDetailsFeatureState.INITIAL
        )
    }
}
