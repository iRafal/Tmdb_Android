package com.tmdb_test.data.db.object_box.movie

import com.tmdb_test.data.db.object_box.movie.dao.MovieDao
import com.tmdb_test.data.db.object_box.movie.dao.MovieDaoImpl
import com.tmdb_test.data.db.object_box.util.BaseObjectBoxTest
import com.tmdb_test.data.db.object_box.util.ModelUtil
import io.objectbox.kotlin.boxFor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDaoTest : BaseObjectBoxTest() {
    private lateinit var movieDao: MovieDao

    override fun setUp() {
        super.setUp()
        movieDao = MovieDaoImpl(store.boxFor())
    }

    @Test
    fun `insert duplicate`() = runTest {
        val input = ModelUtil.movieEntity
        movieDao.insert(input)

        movieDao.getAll().run {
            assertTrue(this.size == 1)
            assertEquals(input.movieId, this.first().movieId)
        }

        movieDao.insert(input)
        movieDao.getAll().run {
            assertTrue(this.size == 1)
            assertEquals(input.movieId, this.first().movieId)
        }
    }

    @Test
    fun `observe all movies success`() = runTest {
        val expected = listOf(ModelUtil.movieEntity)
        movieDao.insert(expected)

        val actual = movieDao.observeAll().take(1).firstOrNull()
        assertEquals(expected, actual)
    }

    @Test
    fun `observe all movies failure`() = runTest {
        val actual = movieDao.observeAll().take(1).firstOrNull()
        assertEquals(emptyList<MovieEntity>(), actual)
    }

    @Test
    fun `get all movies success`() = runTest {
        val expected = listOf(ModelUtil.movieEntity)
        movieDao.insert(expected)

        val actual = movieDao.getAll()
        assertEquals(expected, actual)
    }

    @Test
    fun `get all movies returns empty result`() = runTest {
        val actual = movieDao.getAll()
        assertEquals(emptyList<MovieEntity>(), actual)
    }

    @Test
    fun `get movie by id success`() = runTest {
        val expected = ModelUtil.movieEntity
        movieDao.insert(expected)

        val actual = movieDao.getById(ModelUtil.movieId)
        assertEquals(expected, actual)
    }

    @Test
    fun `get movie by id returns null`() = runTest {
        val actual = movieDao.getById(ModelUtil.movieId)
        assertNull(actual)
    }

    @Test
    fun `delete movies success`() = runTest {
        val input = ModelUtil.movieEntity
        movieDao.insert(input)
        assertEquals(input, movieDao.getById(ModelUtil.movieId))
        movieDao.delete(input)
        assertTrue(movieDao.getAll().isEmpty())
    }
}