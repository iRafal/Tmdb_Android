package com.tmdb.data.api.config.url.provider.discover

class DiscoverUrlProviderImpl(discoverBaseUrl: String) : DiscoverUrlProvider {
    override val discoverMovieUrl: String = "${discoverBaseUrl}discover/movie"
    override val discoverTvUrl: String = "${discoverBaseUrl}discover/tv"
}
