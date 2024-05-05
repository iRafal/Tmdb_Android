package com.tmdb.feature.home.ui.mapping

import com.tmdb.feature.home.ui.data.mapping.MovieDataItemsToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataItemsToHomeModelMapperImpl
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapper
import com.tmdb.feature.home.ui.data.mapping.MovieDataToHomeModelMapperImpl
import com.tmdb.feature.home.ui.util.ModelUtil
import kotlin.test.Test
import kotlin.test.assertEquals

class MovieDataItemsToHomeModelMapperTest {

    private val itemMapper: MovieDataToHomeModelMapper = MovieDataToHomeModelMapperImpl()
    private val listMapper: MovieDataItemsToHomeModelMapper = MovieDataItemsToHomeModelMapperImpl(itemMapper)

    @Test
    fun mapModel() {
        val input = listOf(ModelUtil.movieDataModel)
        val actual = listMapper.map(input)
        val expected = listOf(ModelUtil.uiModelMovie)

        assertEquals(expected = expected, actual = actual)
    }
}
