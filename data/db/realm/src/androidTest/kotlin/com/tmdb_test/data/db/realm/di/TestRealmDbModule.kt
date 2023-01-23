package com.tmdb_test.data.db.realm.di

import android.content.Context
import com.tmdb_test.data.db.realm.di.modules.RealmDbModule
import com.tmdb_test.data.db.realm.movie.dao.MovieDao
import com.tmdb_test.data.db.realm.movie.dao.MovieDaoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RealmDbModule::class]
)
object TestRealmDbModule {

    @Singleton
    @Provides
    fun providesRealmConfig(
        @ApplicationContext applicationContext: Context,
    ): RealmConfiguration {
        MoviesRealmDbConfig.initRealm(applicationContext)
        return MoviesRealmDbConfig.realmTestConfig()
    }

    @Provides
    fun dataBase(realmConfig: RealmConfiguration): Realm = Realm.getInstance(realmConfig)

    @Provides
    fun movieDao(impl: MovieDaoImpl): MovieDao = impl
}