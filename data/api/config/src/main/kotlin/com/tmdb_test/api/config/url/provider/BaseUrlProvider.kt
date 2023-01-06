package com.tmdb_test.api.config.url.provider

interface BaseUrlProvider {
    val discoverApiUrl: String
    val genreApiUrl: String
    val movieApiUrl: String
    val personApiUrl: String
    val apiImageUrl: String
}