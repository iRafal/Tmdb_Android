package com.tmdb.data.source.local.impl.realm.di.component

import android.content.Context
import com.tmdb.data.db.realm.di.modules.DbModule
import com.tmdb.data.source.local.impl.realm.di.module.LocalDataSourceModule
import com.tmdb.util.di.qualifiers.ApplicationContext
import com.tmdb.util.di.qualifiers.ApplicationScope
import dagger.BindsInstance
import dagger.Component


@[ApplicationScope Component(modules = [LocalDataSourceModule::class, DbModule::class])]
interface LocalDataSourceComponent: HasLocalDataSources {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(@ApplicationContext dependency: Context): Builder
        fun build(): LocalDataSourceComponent
    }
}
