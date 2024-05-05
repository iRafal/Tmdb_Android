package com.tmdb.data.api.config.url.provider.person

class PersonUrlProviderImpl(private val personBaseUrl: String) : PersonUrlProvider {
    override fun personDetailsUrl(personId: Int): String = "${personBaseUrl}person/$personId}"
}
