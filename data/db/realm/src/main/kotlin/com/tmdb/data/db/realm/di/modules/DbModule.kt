package com.tmdb.data.db.realm.di.modules

import android.content.Context
import com.tmdb.data.db.realm.di.MoviesRealmDbConfig
import com.tmdb.data.db.realm.movie.dao.MovieDao
import com.tmdb.data.db.realm.movie.dao.MovieDaoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module
object DbModule {
    @Provides
    fun providesRealmConfig(
        @ApplicationContext applicationContext: Context
    ): RealmConfiguration {
        MoviesRealmDbConfig.initRealm(applicationContext)
        return MoviesRealmDbConfig.realmConfig()
    }

    @Provides
    fun dataBase(realmConfig: RealmConfiguration): Realm = Realm.getInstance(realmConfig)

    @Provides
    fun movieDao(impl: MovieDaoImpl): MovieDao = impl
}

fun dbModule() = module {
    factory<RealmConfiguration> {
        MoviesRealmDbConfig.initRealm(get<Context>())
        MoviesRealmDbConfig.realmConfig()
    }
    factory<Realm> { Realm.getInstance(get<RealmConfiguration>()) }
    factory<MovieDao> { MovieDaoImpl(get<RealmConfiguration>()) }
}
