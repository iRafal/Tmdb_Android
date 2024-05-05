package com.tmdb.di.component.store

import android.content.Context
import com.tmdb.data.source.local.implRoom.di.component.HasLocalDataSources
import com.tmdb.data.source.remote.implKtor.di.component.HasRemoteDataSources
import com.tmdb.store.di.module.app.AppStoreModule
import com.tmdb.ui.core.di.base.HasAppStore
import com.tmdb.util.di.qualifiers.ApplicationContext
import com.tmdb.util.di.qualifiers.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    dependencies = [AppStoreComponent.Dependencies::class],
    modules = [AppStoreModule::class]
)
interface AppStoreComponent : HasAppStore {

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: Dependencies): Builder

        @BindsInstance
        fun appContext(@ApplicationContext dependency: Context): Builder

        fun build(): AppStoreComponent
    }

    interface Dependencies: HasRemoteDataSources, HasLocalDataSources
}
