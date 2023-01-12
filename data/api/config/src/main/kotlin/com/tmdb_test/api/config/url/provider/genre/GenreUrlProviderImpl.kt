package com.tmdb_test.api.config.url.provider.genre

import javax.inject.Inject

class GenreUrlProviderImpl @Inject constructor(genreBaseUrl: String) : GenreUrlProvider {
    override val genreMovieListUrl: String = "${genreBaseUrl}genre/movie/list"
    override val genreTvListUrl: String = "${genreBaseUrl}genre/tv/list"
}