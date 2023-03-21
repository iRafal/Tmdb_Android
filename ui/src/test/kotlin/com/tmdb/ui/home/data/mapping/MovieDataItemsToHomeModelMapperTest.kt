package com.tmdb.ui.home.data.mapping

import com.tmdb.ui.util.ModelUtil
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDataItemsToHomeModelMapperTest {

    private val itemMapper: MovieDataToHomeModelMapper = movieDataToHomeModelMapperImpl()
    private val listMapper: MovieDataItemsToHomeModelMapper =
        movieDataItemsToHomeModelMapperImpl(itemMapper)

    @Test
    fun mapModel() {
        val input = listOf(ModelUtil.movieDataModel)
        val actual = listMapper.invoke(input)
        val expected = listOf(ModelUtil.uiModelMovie)
        assertEquals(expected, actual)
    }
}