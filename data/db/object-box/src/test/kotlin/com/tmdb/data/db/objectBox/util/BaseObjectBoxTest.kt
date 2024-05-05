package com.tmdb.data.db.objectBox.util

import com.tmdb.data.db.objectBox.di.UnitTestServiceLocator
import com.tmdb.data.db.objectBox.movie.MyObjectBox
import io.objectbox.BoxStore
import io.objectbox.config.DebugFlags
import java.io.File
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseObjectBoxTest {
    private val dispatcher = UnitTestServiceLocator.textDispatcher
    protected lateinit var store: BoxStore

    @BeforeTest
    open fun setUp() {
        BoxStore.deleteAllFiles(TEST_DIRECTORY)
        store = MyObjectBox.builder()
            .directory(TEST_DIRECTORY)
            .debugFlags(DebugFlags.LOG_QUERIES or DebugFlags.LOG_QUERY_PARAMETERS)
            .build()
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    open fun tearDown() {
        store.close()
        BoxStore.deleteAllFiles(TEST_DIRECTORY)
        Dispatchers.resetMain()
    }

    companion object {
        private val TEST_DIRECTORY = File("objectbox-example/test-db")
    }
}
