package com.tmdb.di.component.app

import android.content.Context
import androidx.work.WorkerFactory
import coil3.ImageLoader
import com.tmdb.MovieApp
import com.tmdb.di.component.app.module.AppModule
import com.tmdb.ui.core.di.base.HasAppContext
import com.tmdb.ui.core.di.base.HasAppStore
import com.tmdb.MainActivity
import com.tmdb.ui.core.di.module.ImageLoadingModule
import com.tmdb.util.di.qualifiers.ApplicationContext
import com.tmdb.util.di.qualifiers.ApplicationScope
import dagger.Component

@Component(modules = [AppModule::class], dependencies = [AppComponentDependencies::class])
@ApplicationScope
interface AppComponent: HasAppContext {

    @get:ApplicationScope
    @get:ImageLoadingModule.CoilOkHttpImageLoader
    val imageLoader: ImageLoader

    val workerFactory: WorkerFactory

    fun inject(inject: MovieApp)
    fun inject(inject: MainActivity)

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: AppComponentDependencies): Builder

        fun build(): AppComponent
    }
}

interface AppComponentDependencies: HasAppStore {
    @get:ApplicationContext
    val applicationContext: Context
}
