package com.tmdb.util.di.qualifiers

import javax.inject.Qualifier
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY_GETTER
import kotlin.annotation.AnnotationTarget.PROPERTY_SETTER
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER


@[Qualifier Target(FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER, VALUE_PARAMETER, FIELD)]
annotation class ApplicationContext
