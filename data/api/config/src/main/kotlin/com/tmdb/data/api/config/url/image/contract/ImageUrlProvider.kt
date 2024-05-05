package com.tmdb.data.api.config.url.image.contract

import com.tmdb.data.api.config.url.image.contract.PosterImageSize.W_500

/*
    https://www.themoviedb.org/talk/5aeaaf56c3a3682ddf0010de
 */
interface ImageUrlProvider {
    fun posterUrl(path: String, size: PosterImageSize = W_500): String
}
