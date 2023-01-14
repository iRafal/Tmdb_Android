package com.tmdb_test.api.model.util.serializer

import com.tmdb_test.api.model.movie.MovieStatus
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object MovieStatusSerializer : KSerializer<MovieStatus?> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(JsonKeys.STATUS, PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: MovieStatus?) {
        when (value) {
            MovieStatus.RUMORED -> RUMORED
            MovieStatus.PLANNED -> PLANNED
            MovieStatus.IN_PRODUCTION -> IN_PRODUCTION
            MovieStatus.POST_PRODUCTION -> POST_PRODUCTION
            MovieStatus.RELEASED -> RELEASED
            MovieStatus.CANCELED -> CANCELED
            null -> null
        }?.let { name -> encoder.encodeString(name) }
    }

    override fun deserialize(decoder: Decoder): MovieStatus? = when (decoder.decodeString()) {
        RUMORED -> MovieStatus.RUMORED
        PLANNED -> MovieStatus.PLANNED
        IN_PRODUCTION -> MovieStatus.IN_PRODUCTION
        POST_PRODUCTION -> MovieStatus.POST_PRODUCTION
        RELEASED -> MovieStatus.RELEASED
        CANCELED -> MovieStatus.CANCELED
        else -> null
    }

    private const val RUMORED = "Rumored"
    private const val PLANNED = "Planned"
    private const val IN_PRODUCTION = "In Production"
    private const val POST_PRODUCTION = "Post Production"
    private const val RELEASED = "Released"
    private const val CANCELED = "Canceled"
}
