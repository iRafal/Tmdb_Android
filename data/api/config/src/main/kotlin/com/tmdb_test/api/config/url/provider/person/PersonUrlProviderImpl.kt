package com.tmdb_test.api.config.url.provider.person

import javax.inject.Inject

class PersonUrlProviderImpl @Inject constructor(
    private val personBaseUrl: String
) : PersonUrlProvider {
    override fun personDetailsUrl(personId: Int): String = "${personBaseUrl}person/$personId}"
}