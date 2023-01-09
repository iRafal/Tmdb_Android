package com.tmdb_test.feature.home.store.reducer.util

import com.tmdb_test.api.model.genre.Genre
import com.tmdb_test.api.model.movie.Movie
import com.tmdb_test.api.model.movie.MovieStatus.Released
import com.tmdb_test.api.model.movie.ProductionCompany
import com.tmdb_test.api.model.movie.ProductionCountry
import com.tmdb_test.api.model.movie.SpokenLanguage
import com.tmdb_test.data.model.MovieDataModel

object ModelUtil {
    val movieDataModel = MovieDataModel(
        id = 550,
        title = "Fight Club",
        voteAverage = 7.8,
        releaseDate = "1999-10-12",
        posterPath = null,
    )

    val movieModel = Movie(
        isAdult = false,
        backdropPath = "/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg",
        belongsToCollection = null,
        budget = 63000000.0,
        genres = listOf(Genre(id = 18, name = "Drama")),
        homepage = "",
        id = 550,
        imdbId = "tt0137523",
        originalLanguage = "en",
        originalTitle = "Fight Club",
        overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
        popularity = 0.5,
        posterPath = null,
        productionCompanies = listOf(
            ProductionCompany(
                id = 508,
                logoPath = "/7PzJdsLGlR7oW4J0J5Xcd0pHGRg.png",
                name = "Regency Enterprises",
                originCountry = "US",
            ),
            ProductionCompany(
                id = 711,
                logoPath = null,
                name = "Fox 2000 Pictures",
                originCountry = "",
            ),
        ),
        productionCountries = listOf(
            ProductionCountry(
                iso_3166_1 = "US",
                name = "United States of America"
            )
        ),
        releaseDate = "1999-10-12",
        revenue = 100853753,
        runtime = 139,
        spokenLanguages = listOf(SpokenLanguage(iso_639_1 = "en", name = "English")),
        status = Released,
        tagline = "How much can you know about yourself if you've never been in a fight?",
        title = "Fight Club",
        isVideo = false,
        voteAverage = 7.8,
        voteCount = 3439
    )
}