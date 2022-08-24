package com.tmdb_test.util.compose

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.Dp
import com.tmdb_test.util.compose.test.ComposeTestSemantics

fun SemanticsNodeInteraction.assertHasLongClickAction(): SemanticsNodeInteraction =
    assert(SemanticsMatcher.keyIsDefined(SemanticsActions.OnLongClick))

fun SemanticsNodeInteraction.performLongClick(): SemanticsNodeInteraction =
    performTouchInput { longClick() }

fun SemanticsNodeInteraction.assertTintResId(@ColorRes colorResId: Int): SemanticsNodeInteraction {
    return assert(SemanticsMatcher.expectValue(ComposeTestSemantics.tintResId, colorResId))
}

fun SemanticsNodeInteraction.assertTintColor(tintColor: Color): SemanticsNodeInteraction {
    return assert(SemanticsMatcher.expectValue(ComposeTestSemantics.tintColor, tintColor))
}

fun SemanticsNodeInteraction.assertBackgroundColor(backgroundColor: Color): SemanticsNodeInteraction {
    return assert(
        SemanticsMatcher.expectValue(
            ComposeTestSemantics.backgroundColor,
            backgroundColor
        )
    )
}

fun SemanticsNodeInteraction.assertImageVector(imageVector: ImageVector): SemanticsNodeInteraction {
    return assert(SemanticsMatcher.expectValue(ComposeTestSemantics.imageVector, imageVector))
}

fun SemanticsNodeInteraction.assertIconResId(@DrawableRes iconResId: Int): SemanticsNodeInteraction {
    return assert(SemanticsMatcher.expectValue(ComposeTestSemantics.iconResId, iconResId))
}

fun SemanticsNodeInteraction.assertPaddingAll(paddingAll: Dp): SemanticsNodeInteraction {
    return assert(SemanticsMatcher.expectValue(ComposeTestSemantics.paddingAll, paddingAll))
}
