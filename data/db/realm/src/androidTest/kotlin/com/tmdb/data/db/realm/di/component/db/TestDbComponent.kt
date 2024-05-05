package com.tmdb.data.db.realm.di.component.db

import com.tmdb.data.db.realm.di.module.TestDbModule
import com.tmdb.data.db.realm.migration.RealmDbMigrationTest
import com.tmdb.data.db.realm.movie.MovieEntityTest
import com.tmdb.data.db.realm.movie.dao.MovieDao
import dagger.Subcomponent
import io.realm.Realm


@[Subcomponent(modules = [TestDbModule::class])]
interface TestDbComponent {
    val movieDao: MovieDao
    val movieDb: Realm

    fun inject(inject: RealmDbMigrationTest)
    fun inject(inject: MovieEntityTest)

    @Subcomponent.Builder
    interface Builder {
        fun build(): TestDbComponent
    }
}
