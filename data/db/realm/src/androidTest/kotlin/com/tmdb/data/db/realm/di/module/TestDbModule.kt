package com.tmdb.data.db.realm.di.module

import android.content.Context
import androidx.test.espresso.internal.inject.InstrumentationContext
import com.tmdb.data.db.realm.di.MoviesRealmDbConfig
import com.tmdb.data.db.realm.di.modules.DbModule
import com.tmdb.data.db.realm.movie.dao.MovieDao
import com.tmdb.data.db.realm.movie.dao.MovieDaoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.realm.Realm
import io.realm.RealmConfiguration

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DbModule::class]
)
@Module
object TestDbModule {

    @Provides
    fun providesRealmConfig(
        @InstrumentationContext applicationContext: Context,
    ): RealmConfiguration {
        MoviesRealmDbConfig.initRealm(applicationContext)
        return MoviesRealmDbConfig.realmTestConfig()
    }

    @Provides
    fun dataBase(realmConfig: RealmConfiguration): Realm = Realm.getInstance(realmConfig)

    @Provides
    fun movieDao(impl: MovieDaoImpl): MovieDao = impl
}
