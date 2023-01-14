package com.tmdb_test.api.config.url.image.impl

import com.tmdb_test.api.config.url.image.contract.ImageUrlProvider
import com.tmdb_test.api.config.url.image.contract.PosterImageSize


class ImageUrlProviderImpl(private val baseUrl: String) : ImageUrlProvider {
    override fun posterUrl(path: String, size: PosterImageSize): String {
        val newBaseUrl = if (baseUrl.endsWith("/")) baseUrl else "$baseUrl/"
        val newPath = if (path.startsWith("/")) path else "/$path"
        return "$newBaseUrl${size.sizeName}$newPath"
    }
}