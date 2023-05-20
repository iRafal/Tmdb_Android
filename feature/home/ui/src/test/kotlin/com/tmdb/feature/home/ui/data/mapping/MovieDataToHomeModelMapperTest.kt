package com.tmdb.feature.home.ui.data.mapping

import com.tmdb.feature.home.ui.util.ModelUtil
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDataToHomeModelMapperTest {
    private val mapper: MovieDataToHomeModelMapper = movieDataToHomeModelMapperImpl()

    @Test
    fun mapModel() {
        val input = ModelUtil.movieDataModel
        val actual = mapper.invoke(input)
        val expected = ModelUtil.uiModelMovie
        assertEquals(expected, actual)
    }
}