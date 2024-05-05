package com.tmdb.ui.core.di.base

import android.content.Context

interface DependenciesProvider<T> {
    fun provide(): T
}

fun <D> Context.provideDependencies(): D =
    getDependenciesProviderFromContext<D>(this).provide()

fun <D> getDependenciesProviderFromContext(context: Context): DependenciesProvider<D> =
    getDependenciesProviderFromContextInternal(context)

private inline fun <T, reified D : DependenciesProvider<T>> getDependenciesProviderFromContextInternal(
    context: Context
): DependenciesProvider<T> {
    check(context is D) { throw IllegalStateException("Please register provider for ${D::class.java} dependency") }
    return context
}
