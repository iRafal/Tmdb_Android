package com.tmdb_test.api.config.url.image.impl

import com.tmdb_test.api.config.url.image.contract.ImageUrlProvider
import com.tmdb_test.api.config.url.image.contract.PosterImageSize


class ImageUrlProviderImpl(private val baseUrl: String): ImageUrlProvider {
    override fun posterUrl(path: String, size: PosterImageSize): String {
        return "$baseUrl${size.sizeName}/$path"
    }
}