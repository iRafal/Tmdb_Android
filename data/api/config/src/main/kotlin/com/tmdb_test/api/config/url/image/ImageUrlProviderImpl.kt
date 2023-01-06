package com.tmdb_test.api.config.url.image


class ImageUrlProviderImpl(private val baseUrl: String): ImageUrlProvider {
    override fun posterUrl(path: String, size: PosterImageSize): String {
        return "$baseUrl${size.sizeName}/$path"
    }
}