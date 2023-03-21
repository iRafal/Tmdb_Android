package com.tmdb.data.db.room.type.converters

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

object LocalDateConverter {

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDate?): String? {
        return dateTime?.toString()
    }

    @TypeConverter
    fun fromTimeStamp(timeStamp: String?): LocalDate? {
        timeStamp ?: return null
        return LocalDate.parse(timeStamp)
    }
}