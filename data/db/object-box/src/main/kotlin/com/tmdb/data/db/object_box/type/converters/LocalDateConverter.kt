package com.tmdb.data.db.object_box.type.converters

import io.objectbox.converter.PropertyConverter
import kotlinx.datetime.LocalDate

class LocalDateConverter : PropertyConverter<LocalDate?, String?> {
    override fun convertToEntityProperty(databaseValue: String?): LocalDate? {
        databaseValue ?: return null
        return LocalDate.parse(databaseValue)
    }

    override fun convertToDatabaseValue(entityProperty: LocalDate?): String? {
        return entityProperty?.toString()
    }
}