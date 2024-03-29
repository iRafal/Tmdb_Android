package com.tmdb.data.db.objectBox.type.converters

import io.objectbox.converter.PropertyConverter
import kotlinx.datetime.LocalDateTime

class LocalDateTimeConverter : PropertyConverter<LocalDateTime?, String?> {
    override fun convertToEntityProperty(databaseValue: String?): LocalDateTime? {
        databaseValue ?: return null
        return LocalDateTime.parse(databaseValue)
    }

    override fun convertToDatabaseValue(entityProperty: LocalDateTime?): String? {
        return entityProperty?.toString()
    }
}
