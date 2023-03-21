package com.tmdb.data.source.remote.impl_ktor.util.model

import com.tmdb.api.model.genre.Genre
import com.tmdb.api.model.movie.Movie
import com.tmdb.api.model.movie.MovieStatus.RELEASED
import com.tmdb.api.model.movie.ProductionCompany
import com.tmdb.api.model.movie.ProductionCountry
import com.tmdb.api.model.movie.SpokenLanguage
import com.tmdb.api.model.person.Person
import com.tmdb.api.model.person.PersonGender.MALE
import kotlinx.datetime.LocalDate

object ModelUtil {
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
        releaseDate = LocalDate.parse("1999-10-12"),
        revenue = 100853753,
        runtime = 139,
        spokenLanguages = listOf(SpokenLanguage(iso_639_1 = "en", name = "English")),
        status = RELEASED,
        tagline = "How much can you know about yourself if you've never been in a fight?",
        title = "Fight Club",
        isVideo = false,
        voteAverage = 7.8,
        voteCount = 3439
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
        biography = "William Bradley \"Brad\" Pitt (born December 18, 1963) is an American actor and film producer. Pitt has received two Academy Award nominations and four Golden Globe Award nominations, winning one. He has been described as one of the world's most attractive men, a label for which he has received substantial media attention. Pitt began his acting career with television guest appearances, including a role on the CBS prime-time soap opera Dallas in 1987. He later gained recognition as the cowboy hitchhiker who seduces Geena Davis's character in the 1991 road movie Thelma & Louise. Pitt's first leading roles in big-budget productions came with A River Runs Through It (1992) and Interview with the Vampire (1994). He was cast opposite Anthony Hopkins in the 1994 drama Legends of the Fall, which earned him his first Golden Globe nomination. In 1995 he gave critically acclaimed performances in the crime thriller Seven and the science fiction film 12 Monkeys, the latter securing him a Golden Globe Award for Best Supporting Actor and an Academy Award nomination.\n\nFour years later, in 1999, Pitt starred in the cult hit Fight Club. He then starred in the major international hit as Rusty Ryan in Ocean's Eleven (2001) and its sequels, Ocean's Twelve (2004) and Ocean's Thirteen (2007). His greatest commercial successes have been Troy (2004) and Mr. & Mrs. Smith (2005).\n\nPitt received his second Academy Award nomination for his title role performance in the 2008 film The Curious Case of Benjamin Button. Following a high-profile relationship with actress Gwyneth Paltrow, Pitt was married to actress Jennifer Aniston for five years. Pitt lives with actress Angelina Jolie in a relationship that has generated wide publicity. He and Jolie have six children—Maddox, Pax, Zahara, Shiloh, Knox, and Vivienne.\n\nSince beginning his relationship with Jolie, he has become increasingly involved in social issues both in the United States and internationally. Pitt owns a production company named Plan B Entertainment, whose productions include the 2007 Academy Award winning Best Picture, The Departed.",
        popularity = 10.647,
        placeOfBirth = "Shawnee, Oklahoma, USA",
        profilePath = "/kU3B75TyRiCgE270EyZnHjfivoq.jpg",
        isAdult = false,
        imdbId = "nm0000093",
        homepage = null,
    )
}