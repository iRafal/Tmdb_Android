package com.tmdb_test.data.api.url.provider.image

/*
  https://www.themoviedb.org/talk/5aeaaf56c3a3682ddf0010de
 */
interface ImageUrlProvider {
    fun posterUrl(path: String, size: PosterImageSize = PosterImageSize.W_500, ): String
}