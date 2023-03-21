package com.tmdb.api.config.url.provider.genre

class GenreUrlProviderImpl(genreBaseUrl: String) : GenreUrlProvider {
    override val genreMovieListUrl: String = "${genreBaseUrl}genre/movie/list"
    override val genreTvListUrl: String = "${genreBaseUrl}genre/tv/list"
}