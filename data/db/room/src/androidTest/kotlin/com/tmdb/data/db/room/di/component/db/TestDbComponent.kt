package com.tmdb.data.db.room.di.component.db

import com.tmdb.data.db.room.MovieDb
import com.tmdb.data.db.room.di.module.TestDbModule
import com.tmdb.data.db.room.migration.AppDbMigrationTest
import com.tmdb.data.db.room.movie.MovieDao
import com.tmdb.data.db.room.movie.MovieEntityTest
import dagger.Subcomponent


@[Subcomponent(modules = [TestDbModule::class])]
interface TestDbComponent {
    val movieDao: MovieDao
    val movieDb: MovieDb

    fun inject(inject: AppDbMigrationTest)
    fun inject(inject: MovieEntityTest)

    @Subcomponent.Builder
    interface Builder {
        fun build(): TestDbComponent
    }
}
