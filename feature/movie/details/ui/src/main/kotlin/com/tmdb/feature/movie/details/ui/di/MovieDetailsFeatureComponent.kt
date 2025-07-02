package com.tmdb.feature.movie.details.ui.di

import android.content.Context
import com.tmdb.feature.movie.details.ui.MovieDetailsViewModel
import com.tmdb.ui.core.di.base.HasAppStore
import com.tmdb.ui.core.di.base.provideDependencies
import com.tmdb.ui.core.di.qualifiers.FeatureScope


object MovieDetailsFeatureDi {

    @FeatureScope
    @dagger.Component(
        modules = [MovieDetailsFeatureModule::class],
        dependencies = [Component.Dependencies::class]
    )
    interface Component {
        val movieDetailsViewModelCreator: MovieDetailsViewModel.Creator

        @dagger.Component.Factory
        interface Factory {
            fun create(dependencies: Dependencies): Component
        }

        interface Dependencies : HasAppStore
    }

    fun fromContext(context: Context): Component {
        check(context is Component.Dependencies) {
            "[$context], doesn't provide dependencies [${Component.Dependencies::class}]," +
                    " for component ${Component::class}"
        }
        return DaggerMovieDetailsFeatureDi_Component.factory().create(context)
    }

    fun fromContextWithContract(context: Context): Component {
        return DaggerMovieDetailsFeatureDi_Component.factory().create(context.provideDependencies())
    }
}

