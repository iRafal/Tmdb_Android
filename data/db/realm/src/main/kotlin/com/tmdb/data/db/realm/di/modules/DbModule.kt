package com.tmdb.data.db.realm.di.modules

import android.content.Context
import com.tmdb.data.db.realm.di.MoviesRealmDbConfig
import com.tmdb.data.db.realm.movie.dao.MovieDao
import com.tmdb.data.db.realm.movie.dao.MovieDaoImpl
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.dsl.module

fun dbModule() = module {
    factory<RealmConfiguration> {
        MoviesRealmDbConfig.initRealm(get<Context>())
        MoviesRealmDbConfig.realmConfig()
    }
    factory<Realm> { Realm.getInstance(get<RealmConfiguration>()) }
    factory<MovieDao> { MovieDaoImpl(get<RealmConfiguration>()) }
}
