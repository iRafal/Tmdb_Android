package com.tmdb_test.data.model.di.modules

import com.tmdb_test.api.config.url.image.contract.ImageUrlProvider
import com.tmdb_test.data.model.mapping.movie.MovieApiToDataModelMapper
import com.tmdb_test.data.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb_test.data.model.mapping.movie.movieApiToDataModelMapperImpl
import com.tmdb_test.data.model.mapping.movie.moviesApiToDataStateMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MovieApiMappingModule {

    @Provides
    @JvmStatic
    fun movieApiToDataModelMapper(
        imageUrlProvider: ImageUrlProvider
    ): MovieApiToDataModelMapper = movieApiToDataModelMapperImpl(imageUrlProvider)

    @Provides
    @JvmStatic
    fun moviesApiToDataStateMapper(
        movieApiToDataModelMapper: @JvmSuppressWildcards MovieApiToDataModelMapper,
    ): @JvmSuppressWildcards MoviesApiToDataStateMapper = moviesApiToDataStateMapperImpl(movieApiToDataModelMapper)
}