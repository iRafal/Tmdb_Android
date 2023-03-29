package com.tmdb.data.db.realm.movie

import com.tmdb.data.db.realm.di.modules.RealmDbModule
import com.tmdb.data.db.realm.util.ModelUtil
import com.tmdb.data.db.realm.di.DispatchersTestModule
import com.tmdb.data.db.realm.movie.dao.MovieDao
import com.tmdb.utill.di.modules.DispatchersModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.realm.Realm
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@UninstallModules(RealmDbModule::class, DispatchersModule::class)
@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class MovieEntityTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var movieDao: MovieDao

    @Inject
    lateinit var realm: Realm

    @Inject
    @DispatchersTestModule.DispatcherTestStandard
    lateinit var dispatcher: TestDispatcher

    private val movieEntity = ModelUtil.movieEntity
    private val movieId = ModelUtil.movieId

    @Before
    fun setup() {
        hiltRule.inject()
        Dispatchers.setMain(dispatcher)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() = runTest {
        movieDao.delete()
        realm.close()
        Dispatchers.resetMain()
    }

    @Test
    @Throws(IOException::class)
    fun write_GetMovieById() = runTest {
        movieDao.insert(movieEntity)
        movieDao.getById(movieId).run {
        assertEquals(movieEntity, this) }
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_GetMovieById() = runTest {
        movieDao.getById(movieId).run { assertNull(this) }
    }

    @Test
    @Throws(IOException::class)
    fun write_GetAllMovieEntities() = runTest {
        movieDao.insert(movieEntity)
        movieDao.getAll().run { assertTrue(this.contains(movieEntity)) }
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_GetAllMovies() = runTest {
        movieDao.getAll().run { assertEquals(this, emptyList<MovieEntity>()) }
    }

    @Test
    @Throws(IOException::class)
    fun write_ObserveAllMovies() = runTest {
        movieDao.insert(movieEntity)
        val allMovies = movieDao.observeAll().take(1).firstOrNull()
        assertTrue(allMovies?.contains(movieEntity) == true)
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_ObserveAllMovies() = runTest {
        val allMovies = movieDao.observeAll().take(1).firstOrNull()
        advanceUntilIdle()
        assertEquals(allMovies, emptyList<MovieEntity>())
    }

    @Test
    @Throws(IOException::class)
    fun write_DeleteMovieById() = runTest {
        movieDao.insert(movieEntity)
        movieDao.getById(movieId).run { assertEquals(movieEntity, this) }
        movieDao.delete(movieEntity)
        movieDao.getById(movieId).run { assertNull(this) }
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_DeleteMovieById() = runTest {
        movieDao.getById(movieId).run { assertNull(this) }
        movieDao.delete(movieEntity)
        movieDao.getById(movieId).run { assertNull(this) }
    }

    @Test
    @Throws(IOException::class)
    fun write_DeleteAllMovies() = runTest {
        movieDao.insert(movieEntity)
        movieDao.getById(movieId).run { assertEquals(movieEntity, this) }
        movieDao.delete()
        movieDao.getById(movieId).also { deletedMovie -> assertNull(deletedMovie) }
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_DeleteAllMovies() = runTest {
        movieDao.getById(movieId).run { assertNull(this) }
        movieDao.delete()
        movieDao.getById(movieId).run { assertNull(this) }
    }
}