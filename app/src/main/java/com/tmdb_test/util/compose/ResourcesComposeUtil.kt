package com.tmdb_test.util.compose

import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


@Composable
fun pluralResource(
    @PluralsRes resId: Int,
    quantity: Int,
    vararg formatArgs: Any?
): String {
    return LocalContext.current.resources.getQuantityString(resId, quantity, *formatArgs)
}