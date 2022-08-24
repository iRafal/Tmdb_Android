package com.tmdb_test.util.compose.test

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import com.tmdb_test.util.compose.test.ComposeTestSemantics.backgroundColor
import com.tmdb_test.util.compose.test.ComposeTestSemantics.iconResId
import com.tmdb_test.util.compose.test.ComposeTestSemantics.imageVector
import com.tmdb_test.util.compose.test.ComposeTestSemantics.paddingAll
import com.tmdb_test.util.compose.test.ComposeTestSemantics.tintColor
import com.tmdb_test.util.compose.test.ComposeTestSemantics.tintResId

object ComposeTestSemantics {
    val tintResId = SemanticsPropertyKey<Int>("tintResId")
    var SemanticsPropertyReceiver.tintResId by tintResId

    val tintColor = SemanticsPropertyKey<Color>("tintColor")
    var SemanticsPropertyReceiver.tintColor by tintColor

    val backgroundColor = SemanticsPropertyKey<Color>("backgroundColor")
    var SemanticsPropertyReceiver.backgroundColor by backgroundColor

    val imageVector = SemanticsPropertyKey<ImageVector>("imageVector")
    var SemanticsPropertyReceiver.imageVector by imageVector

    val iconResId = SemanticsPropertyKey<Int>("iconResId")
    var SemanticsPropertyReceiver.iconResId by iconResId

    val paddingAll = SemanticsPropertyKey<Dp>("paddingAll")
    var SemanticsPropertyReceiver.paddingAll by paddingAll
}

fun Modifier.testTintResId(@ColorRes tint: Int) = semantics { this.tintResId = tint }

fun Modifier.testTintColor(tintColor: Color) = semantics { this.tintColor = tintColor }

fun Modifier.testBackgroundColor(backgroundColor: Color) =
    semantics { this.backgroundColor = backgroundColor }

fun Modifier.testImageVector(imageVector: ImageVector) =
    semantics { this.imageVector = imageVector }

fun Modifier.testIconResId(@DrawableRes iconResId: Int) = semantics { this.iconResId = iconResId }

fun Modifier.testPaddingAll(paddingAll: Dp) = semantics { this.paddingAll = paddingAll }