package com.tmdb.api.config.url.image

import com.tmdb.data.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.data.api.config.url.image.contract.PosterImageSize
import com.tmdb.data.api.config.url.image.impl.ImageUrlProviderImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class ImageUrlProviderTest {
    private val baseUrl = "https://web.site.com/"
    private val imageUrlProvider: ImageUrlProvider = ImageUrlProviderImpl(baseUrl)
    private val path = "pathSegment1/pathSegment2"

    @Test
    fun testBaseUrlWithEndingSlashAndOriginalSize() {
        val actual = imageUrlProvider.posterUrl(path, size = PosterImageSize.ORIGINAL)
        val expected = "$baseUrl${PosterImageSize.ORIGINAL.sizeName}/$path"

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun testBaseUrlWithoutEndingSlash() {
        val baseUrlWithEndingSlash = "https://web.site.com"
        val imageUrlProvider: ImageUrlProvider = ImageUrlProviderImpl(baseUrlWithEndingSlash)
        val path = "pathSegment1/pathSegment2"
        val imageSize = PosterImageSize.ORIGINAL
        val actual = imageUrlProvider.posterUrl(path, size = imageSize)

        val expected = "$baseUrlWithEndingSlash/${imageSize.sizeName}/$path"

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun testImageUrlWith_Width92_Size() {
        val imageSize = PosterImageSize.W_92
        val actual = imageUrlProvider.posterUrl(path, size = imageSize)
        val expected = "$baseUrl${imageSize.sizeName}/$path"

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun testImageUrlWith_Width154_Size() {
        val imageSize = PosterImageSize.W_154
        val actual = imageUrlProvider.posterUrl(path, size = imageSize)
        val expected = "$baseUrl${imageSize.sizeName}/$path"

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun testImageUrlWith_Width185_Size() {
        val imageSize = PosterImageSize.W_185
        val actual = imageUrlProvider.posterUrl(path, size = imageSize)
        val expected = "$baseUrl${imageSize.sizeName}/$path"

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun testImageUrlWith_Width342_Size() {
        val imageSize = PosterImageSize.W_342
        val actual = imageUrlProvider.posterUrl(path, size = imageSize)
        val expected = "$baseUrl${imageSize.sizeName}/$path"

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun testImageUrlWith_Width500_Size() {
        val imageSize = PosterImageSize.W_500
        val actual = imageUrlProvider.posterUrl(path, size = imageSize)
        val expected = "$baseUrl${imageSize.sizeName}/$path"

        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun testImageUrlWith_Width780_Size() {
        val imageSize = PosterImageSize.W_780
        val actual = imageUrlProvider.posterUrl(path, size = imageSize)
        val expected = "$baseUrl${imageSize.sizeName}/$path"

        assertEquals(expected = expected, actual = actual)
    }
}
