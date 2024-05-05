package com.tmdb.data.db.room.converters

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

object LocalDateTimeConverter {

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): String? {
        return dateTime?.toString()
    }

    @TypeConverter
    fun fromTimeStamp(timeStamp: String?): LocalDateTime? {
        timeStamp ?: return null
        return LocalDateTime.parse(timeStamp)
    }
}
