package com.tmdb.data.api.config.url.provider.movie

interface MovieUrlProvider {
    fun movieUrl(movieId: Int): String
    val latestMovieUrl: String
    val nowPlayingMoviesUrl: String
    val nowPopularMoviesUrl: String
    val topRatedMoviesUrl: String
    val upcomingMoviesUrl: String
}
