package com.tmdb.data.model.di.modules

import com.tmdb.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.data.model.mapping.movie.MovieApiToDataModelMapper
import com.tmdb.data.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb.data.model.mapping.movie.movieApiToDataModelMapperImpl
import com.tmdb.data.model.mapping.movie.moviesApiToDataStateMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MovieApiMappingModule {

    @Provides
    fun movieApiToDataModelMapper(
        imageUrlProvider: ImageUrlProvider
    ): MovieApiToDataModelMapper = movieApiToDataModelMapperImpl(imageUrlProvider)

    @Provides
    fun moviesApiToDataStateMapper(
        movieApiToDataModelMapper: @JvmSuppressWildcards MovieApiToDataModelMapper,
    ): @JvmSuppressWildcards MoviesApiToDataStateMapper = moviesApiToDataStateMapperImpl(movieApiToDataModelMapper)
}