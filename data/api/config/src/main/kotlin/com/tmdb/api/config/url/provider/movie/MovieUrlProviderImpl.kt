package com.tmdb.api.config.url.provider.movie

class MovieUrlProviderImpl(private val movieBaseUrl: String) : MovieUrlProvider {
    override fun movieUrl(movieId: Int): String = "${movieBaseUrl}movie/$movieId"
    override val latestMovieUrl: String = "${movieBaseUrl}movie/latest"
    override val nowPlayingMoviesUrl: String = "${movieBaseUrl}movie/now_playing"
    override val nowPopularMoviesUrl: String = "${movieBaseUrl}movie/popular"
    override val topRatedMoviesUrl: String = "${movieBaseUrl}movie/top_rated"
    override val upcomingMoviesUrl: String = "${movieBaseUrl}movie/upcoming"
}