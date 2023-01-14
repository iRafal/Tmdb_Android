package com.tmdb_test.api.config.url.provider.person

class PersonUrlProviderImpl(private val personBaseUrl: String) : PersonUrlProvider {
    override fun personDetailsUrl(personId: Int): String = "${personBaseUrl}person/$personId}"
}