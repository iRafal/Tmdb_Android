package com.tmdb_test.ui.home.data.mapping

import com.tmdb_test.ui.util.ModelUtil
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