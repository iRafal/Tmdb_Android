package com.tmdb.data.db.realm.di.module

import com.tmdb.data.db.realm.di.MoviesRealmDbConfig
import com.tmdb.data.db.realm.movie.dao.MovieDao
import com.tmdb.data.db.realm.movie.dao.MovieDaoImpl
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.dsl.module

fun testDbModule() = module {
    factory<RealmConfiguration> {
        MoviesRealmDbConfig.initRealm(get())
        MoviesRealmDbConfig.realmTestConfig()
    }
    factory<Realm> { Realm.getInstance(get()) }
    factory<MovieDao> { MovieDaoImpl(get()) }
}
