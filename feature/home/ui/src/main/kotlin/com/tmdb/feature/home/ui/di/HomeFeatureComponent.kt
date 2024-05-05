package com.tmdb.feature.home.ui.di

import android.content.Context
import com.tmdb.feature.home.ui.HomeViewModel
import com.tmdb.feature.home.ui.di.module.HomeFeatureModule
import com.tmdb.ui.core.di.base.HasAppStore
import com.tmdb.ui.core.di.base.provideDependencies
import com.tmdb.ui.core.di.qualifiers.FeatureScope


object HomeFeatureDi {
    @FeatureScope
    @dagger.Component(modules = [HomeFeatureModule::class], dependencies = [Component.Dependencies::class])
    interface Component {

        val homeViewModel: HomeViewModel

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
        return DaggerHomeFeatureDi_Component.factory().create(context)
    }

    fun fromContextWithContract(context: Context): Component {
        return DaggerHomeFeatureDi_Component.factory().create(context.provideDependencies())
    }
}
