package com.tmdb.data.api.config.url.provider.base

import com.tmdb.api.config.BuildConfig
import javax.inject.Inject

class BaseUrlProviderImpl @Inject constructor() : BaseUrlProvider {
    override val discoverApiUrl: String = BuildConfig.API_BASE_URL
    override val genreApiUrl: String = BuildConfig.API_BASE_URL
    override val movieApiUrl: String = BuildConfig.API_BASE_URL
    override val personApiUrl: String = BuildConfig.API_BASE_URL
    override val apiImageUrl: String = BuildConfig.API_IMAGE_URL
}
