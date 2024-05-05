package com.tmdb.data.api.implKtor.di.component

import com.tmdb.data.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.data.api.implKtor.discover.DiscoverApi
import com.tmdb.data.api.implKtor.genre.GenreApi
import com.tmdb.data.api.implKtor.movie.MovieApi
import com.tmdb.data.api.implKtor.person.PersonApi


interface HasApiServices {
    val discoverApi: DiscoverApi

    val genreApi: GenreApi

    val movieApi: MovieApi

    val personApi: PersonApi

    val imageUrlProvider: ImageUrlProvider
}
