package com.tmdb_test.feature.home.data

import com.tmdb_test.data.api.model.movie.Movie
import com.tmdb_test.data.api.url.provider.image.ImageUrlProvider
import com.tmdb_test.di.ServiceLocator
import com.tmdb_test.feature.home.content.HomeMovieSection
import com.tmdb_test.feature.home.store.HomeAction
import com.tmdb_test.feature.home.store.HomeFeatureState
import com.tmdb_test.util.map

fun List<Movie>.map(imageUrlProvider: ImageUrlProvider): List<HomeUiData.Movie> {
    return this.map { movie -> movie.map(imageUrlProvider) }
}

fun Movie.map(imageUrlProvider: ImageUrlProvider): HomeUiData.Movie {
    return HomeUiData.Movie(
        id = checkNotNull(this.id),
        title = checkNotNull(this.title),
        averageVote = this.voteAverage ?: 0.0,
        releaseDate = checkNotNull(this.releaseDate),
        posterUrl = this.posterPath?.let {
            imageUrlProvider.posterUrl(it)
        }
    )
}

fun HomeFeatureState.map(): HomeUiData {
    val dataMapper: (List<Movie>) -> List<HomeUiData.Movie> = { movies ->
        movies.map { movie -> movie.map(ServiceLocator.imageUrlProvider) }
    }
    val nowPlayingMoviesState = this.nowPlayingMoviesState.map(dataMapper)
    val nowPopularMoviesState = this.nowPopularMoviesState.map(dataMapper)
    val topRatedMoviesState = this.topRatedMoviesState.map(dataMapper)
    val upcomingMoviesState = this.topRatedMoviesState.map(dataMapper)

    return HomeUiData(
        movieSections = mapOf(
            HomeMovieSection.NOW_PLAYING to nowPlayingMoviesState,
            HomeMovieSection.NOW_POPULAR to nowPopularMoviesState,
            HomeMovieSection.TOP_RATED to topRatedMoviesState,
            HomeMovieSection.UPCOMING to upcomingMoviesState,
        )
    )
}

fun HomeMovieSection.mapToHomeAction(): HomeAction =
    when (this) {
        HomeMovieSection.NOW_PLAYING -> HomeAction.ReloadNowPlayingMovies
        HomeMovieSection.NOW_POPULAR -> HomeAction.ReloadNowPopularMovies
        HomeMovieSection.TOP_RATED -> HomeAction.ReloadTopRatedMovies
        HomeMovieSection.UPCOMING -> HomeAction.ReloadUpcomingMovies
    }


