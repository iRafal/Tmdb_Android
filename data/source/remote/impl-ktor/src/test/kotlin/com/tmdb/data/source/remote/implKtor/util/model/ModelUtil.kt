package com.tmdb.data.source.remote.implKtor.util.model

import com.tmdb.api.model.genre.Genre
import com.tmdb.api.model.movie.Movie
import com.tmdb.api.model.movie.MovieStatus.RELEASED
import com.tmdb.api.model.movie.ProductionCompany
import com.tmdb.api.model.movie.ProductionCountry
import com.tmdb.api.model.movie.SpokenLanguage
import com.tmdb.api.model.person.Person
import com.tmdb.api.model.person.PersonGender.MALE
import com.tmdb.data.model.MovieDataModel
import com.tmdb.data.model.PersonDataModel
import kotlinx.datetime.LocalDate

object ModelUtil {

    const val movieId = 550
    const val title = "Fight Club"
    const val voteAverage = 7.8
    val releaseDate = LocalDate.parse("1999-10-12")
    const val posterUrl = "https://web.page.com/w500/posterUrl"
    const val isNowPlaying = false
    const val isNowPopular = false
    const val isTopRated = false
    const val isUpcoming = false

    val movieModel = Movie(
        isAdult = false,
        backdropPath = "/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg",
        belongsToCollection = null,
        budget = 63000000.0,
        genres = listOf(Genre(id = 18, name = "Drama")),
        homepage = "",
        id = movieId,
        imdbId = "tt0137523",
        originalLanguage = "en",
        originalTitle = title,
        overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into",
        popularity = 0.5,
        posterPath = "posterUrl",
        productionCompanies = listOf(
            ProductionCompany(
                id = 508,
                logoPath = "/7PzJdsLGlR7oW4J0J5Xcd0pHGRg.png",
                name = "Regency Enterprises",
                originCountry = "US"
            ),
            ProductionCompany(
                id = 711,
                logoPath = null,
                name = "Fox 2000 Pictures",
                originCountry = ""
            )
        ),
        productionCountries = listOf(
            ProductionCountry(
                iso_3166_1 = "US",
                name = "United States of America"
            )
        ),
        releaseDate = releaseDate,
        revenue = 100853753,
        runtime = 139,
        spokenLanguages = listOf(SpokenLanguage(iso_639_1 = "en", name = "English")),
        status = RELEASED,
        tagline = "How much can you know about yourself if you've never been in a fight?",
        title = title,
        isVideo = false,
        voteAverage = voteAverage,
        voteCount = 3439
    )

    val movieDataModel = MovieDataModel(
        id = movieId,
        title = title,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        posterUrl = posterUrl
    )

    val personModel = Person(
        birthday = LocalDate.parse("1963-12-18"),
        knownForDepartment = "Acting",
        deathDay = null,
        id = 287,
        name = "Brad Pitt",
        alsoKnownAs = listOf(
            "برد پیت",
            "Бред Питт",
            "Бред Пітт",
            "Buratto Pitto",
            "Брэд Питт",
            "畢·彼特",
            "ブラッド・ピット",
            "브래드 피트",
            "براد بيت",
            "แบรด พิตต์"
        ),
        gender = MALE,
        biography = "William Bradley \"Brad\" Pitt (born December 18, 1963) is an American actor and film producer.",
        popularity = 10.647,
        placeOfBirth = "Shawnee, Oklahoma, USA",
        profilePath = "/kU3B75TyRiCgE270EyZnHjfivoq.jpg",
        isAdult = false,
        imdbId = "nm0000093",
        homepage = null
    )

    val personDataModel = PersonDataModel()
}
