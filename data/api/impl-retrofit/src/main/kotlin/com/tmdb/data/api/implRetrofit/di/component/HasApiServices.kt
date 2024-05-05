package com.tmdb.data.api.implRetrofit.di.component

import com.tmdb.data.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.data.api.implRetrofit.discover.DiscoverApi
import com.tmdb.data.api.implRetrofit.genre.GenreApi
import com.tmdb.data.api.implRetrofit.movie.MovieApi
import com.tmdb.data.api.implRetrofit.person.PersonApi


interface HasApiServices {
    val discoverApi: DiscoverApi

    val genreApi: GenreApi

    val movieApi: MovieApi

    val personApi: PersonApi

    val imageUrlProvider: ImageUrlProvider
}
