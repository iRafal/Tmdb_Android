package com.tmdb.data.source.local.implRoom.di.component

import android.content.Context
import com.tmdb.data.source.local.implRoom.di.module.LocalDataSourceModule
import com.tmdb.util.di.qualifiers.ApplicationContext
import com.tmdb.util.di.qualifiers.ApplicationScope
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(modules = [LocalDataSourceModule::class])
interface LocalDataSourceComponent: HasLocalDataSources {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(@ApplicationContext dependency: Context): Builder
        fun build(): LocalDataSourceComponent
    }
}
