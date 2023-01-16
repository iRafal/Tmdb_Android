package com.tmdb_test.data.db.realm.model

import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.time.LocalDate

@RealmClass
open class MovieEntity(
    @PrimaryKey var id: Int? = null,
    val title: String? = null,
    var voteAverage: Double? = null,
    var releaseDate: LocalDate? = null,
    var posterUrl: String? = null,
)