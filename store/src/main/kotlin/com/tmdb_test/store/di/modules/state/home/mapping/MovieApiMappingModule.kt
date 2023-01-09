package com.tmdb_test.store.di.modules.state.home.mapping

import com.tmdb_test.data.source.model.mapping.movie.MovieApiToDataModelMapper
import com.tmdb_test.data.source.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb_test.data.source.model.mapping.movie.movieApiToDataModelMapperImpl
import com.tmdb_test.data.source.model.mapping.movie.moviesApiToDataStateMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MovieApiMappingModule {

    @Provides
    fun movieApiToDataModelMapper(): MovieApiToDataModelMapper = ::movieApiToDataModelMapperImpl

    @Provides
    fun moviesApiToDataStateMapper(
       movieApiToDataModelMapper: @JvmSuppressWildcards MovieApiToDataModelMapper,
    ): @JvmSuppressWildcards MoviesApiToDataStateMapper = moviesApiToDataStateMapperImpl(movieApiToDataModelMapper)
}