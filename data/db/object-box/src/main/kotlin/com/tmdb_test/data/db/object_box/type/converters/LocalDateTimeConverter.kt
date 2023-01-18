package com.tmdb_test.data.db.object_box.type.converters

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