package com.tmdb.ui.core.compose.resources

import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources

@Composable
fun pluralResource(
    @PluralsRes resId: Int,
    quantity: Int,
    vararg formatArgs: Any?
): String = LocalResources.current.getQuantityString(resId, quantity, *formatArgs)
