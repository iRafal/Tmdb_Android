package com.tmdb_test.data.db.room.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "vote_average") val voteAverage: Double? = null,
    @ColumnInfo(name = "release_date") val releaseDate: LocalDate? = null,
    @ColumnInfo(name = "poster_url") val posterUrl: String? = null,
)