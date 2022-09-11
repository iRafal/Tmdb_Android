package com.tmdb_test.data.api.url.provider

import com.tmdb_test.BuildConfig
import javax.inject.Inject

class BaseUrlProviderImpl @Inject constructor() : BaseUrlProvider {
    override val discoverApiUrl: String = BuildConfig.API_BASE_URL
    override val genreApiUrl: String = BuildConfig.API_BASE_URL
    override val movieApiUrl: String = BuildConfig.API_BASE_URL
    override val personApiUrl: String = BuildConfig.API_BASE_URL
}