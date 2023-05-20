package com.tmdb.ui.core.utli.compose

import androidx.annotation.DrawableRes
import androidx.compose.ui.test.SemanticsMatcher
import com.tmdb.ui.core.compose.test.ComposeTestSemantics


fun hasIconResId(@DrawableRes iconRes: Int): SemanticsMatcher =
    SemanticsMatcher.expectValue(ComposeTestSemantics.iconResId, iconRes)