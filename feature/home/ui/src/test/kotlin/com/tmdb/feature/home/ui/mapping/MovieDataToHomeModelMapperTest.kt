package com.tmdb.feature.home.ui.mapping

import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapperImpl
import com.tmdb.feature.home.ui.util.ModelUtil
import kotlin.test.Test
import kotlin.test.assertEquals

class MovieDataToHomeModelMapperTest {
    private val mapper: MovieDataToHomeModelMapper = MovieDataToHomeModelMapperImpl()

    @Test
    fun mapModel() {
        val input = ModelUtil.movieDataModel
        val actual = mapper.map(input)

        assertEquals(expected = ModelUtil.uiModelMovie, actual = actual)
    }
}
