package com.tmdb_test.data.api.url.provider

interface BaseUrlProvider {
    val discoverApiUrl: String
    val genreApiUrl: String
    val movieApiUrl: String
    val personApiUrl: String
}