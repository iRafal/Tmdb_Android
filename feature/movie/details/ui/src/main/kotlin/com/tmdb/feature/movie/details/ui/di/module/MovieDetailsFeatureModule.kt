package com.tmdb.feature.movie.details.ui.di.module

import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppDbEnvImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.dsl.module

@InstallIn(SingletonComponent::class)
@Module
interface MovieDetailsFeatureModule

fun movieDetailsFeatureModule() = module {
}
