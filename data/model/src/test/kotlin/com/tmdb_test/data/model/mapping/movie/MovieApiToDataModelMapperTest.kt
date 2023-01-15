package com.tmdb_test.data.model.mapping.movie

import com.tmdb_test.api.config.url.image.contract.ImageUrlProvider
import com.tmdb_test.api.config.url.image.impl.ImageUrlProviderImpl
import com.tmdb_test.data.model.util.ModelUtil
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieApiToDataModelMapperTest {
    private val baseUrl = "https://web.site.com"
    private val imageUrlProvider: ImageUrlProvider = ImageUrlProviderImpl(baseUrl)
    private val mapper: MovieApiToDataModelMapper = movieApiToDataModelMapperImpl(imageUrlProvider)

    @Test
    fun testMapApiToDataModel() {
        val input = ModelUtil.movieModel
        val actual = mapper.invoke(input)
        val expected = ModelUtil.movieDataModel
        assertEquals(expected, actual)
    }
}