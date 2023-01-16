package com.tmdb_test.data.db.room.migration

import android.content.Context
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.espresso.internal.inject.InstrumentationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.tmdb_test.data.db.room.MovieDb
import com.tmdb_test.data.db.room.MovieDbMigrations
import com.tmdb_test.data.db.room.di.DispatchersTestModule
import com.tmdb_test.data.db.room.movie.MovieEntity
import com.tmdb_test.data.db.room.util.ModelUtil
import com.tmdb_test.utill.di.modules.DispatchersModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(DispatchersModule::class)
@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class AppDbMigrationTest {

    @Inject
    @DispatchersTestModule.DispatcherTestUnconfined
    lateinit var dispatcher: TestDispatcher

    @Inject
    @InstrumentationContext
    lateinit var context: Context

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        MovieDb::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Before
    fun setup() {
        hiltRule.inject()
        Dispatchers.setMain(dispatcher)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    @Throws(IOException::class)
    fun migrate1To2() = runTest {
        val versionFrom = 1
        val versionTo = 2

        val movieId = ModelUtil.movieId
        val title = ModelUtil.title
        val voteAverage = ModelUtil.voteAverage
        val releaseDate = ModelUtil.releaseDate
        val posterUrl = ModelUtil.posterUrl
        val isNowPlaying = ModelUtil.isNowPlaying
        val isNowPopular = ModelUtil.isNowPopular
        val isTopRated = ModelUtil.isTopRated
        val isUpcoming = ModelUtil.isUpcoming

        val tableName = MovieEntity.MOVIE_TABLE_NAME
        val columId = MovieEntity.MOVIE_TABLE_COLUMN_ID
        val columnTitle = MovieEntity.MOVIE_TABLE_COLUMN_TITLE
        val columnVoteAverage = MovieEntity.MOVIE_TABLE_COLUMN_VOTE_AVERAGE
        val columnReleaseDate = MovieEntity.MOVIE_TABLE_COLUMN_RELEASE_DATE
        val columnPosterUrl = MovieEntity.MOVIE_TABLE_COLUMN_POSTER_URL

        helper.createDatabase(TEST_DB, versionFrom).apply {
            //INFO: db has schema version 1. insert some data using SQL queries.
            //INFO: You cannot use DAO classes because they expect the latest schema.
            execSQL("INSERT INTO $tableName " +
                    "($columId, $columnTitle, $columnVoteAverage, $columnReleaseDate, $columnPosterUrl) " +
                    "VALUES ('$movieId', '$title', '$voteAverage', '$releaseDate', '$posterUrl')")

            //INFO: Prepare for the next version.
            close()
        }

        val db = helper.runMigrationsAndValidate(
            TEST_DB,
            versionTo,
            true,
            MovieDbMigrations.MIGRATION_1_2
        )

        //INFO: MigrationTestHelper automatically verifies the schema changes,
        //INFO: but you need to validate that the data was migrated properly.
        val migratedDb = getMigratedRoomDatabase()
        val actualMovie = migratedDb.movieDao().getAll().firstOrNull()
        checkNotNull(actualMovie)

        assertEquals(actualMovie.id, movieId)
        assertEquals(actualMovie.title, title)
        assertEquals(actualMovie.voteAverage, voteAverage)
        assertEquals(actualMovie.releaseDate, releaseDate)
        assertEquals(actualMovie.posterUrl, posterUrl)
        assertEquals(actualMovie.isNowPlaying, isNowPlaying)
        assertEquals(actualMovie.isNowPopular, isNowPopular)
        assertEquals(actualMovie.isTopRated, isTopRated)
        assertEquals(actualMovie.isUpcoming, isUpcoming)

        migratedDb.close()
        db.close()
    }

//    @Test
//    @Throws(IOException::class)
//    fun migrate2To3() {
//        val versionFrom = 2
//        val versionTo = 3
//
//        helper.createDatabase(TEST_DB, versionFrom).apply { close() }
//        helper.runMigrationsAndValidate(
//            TEST_DB,
//            versionTo,
//            true,
//            AppDb.MIGRATION_1_2,
//            AppDb.MIGRATION_2_3
//        ).close()
//    }

    private fun getMigratedRoomDatabase(): MovieDb {
        return MovieDb.getDbBuilder(
            context,
            TEST_DB
        )
            .addMigrations(MovieDbMigrations.MIGRATION_1_2)
            .build()
            .apply {
                openHelper.writableDatabase
                close()
            }
    }

    companion object {
        private const val TEST_DB = "migration-test.db"
    }
}