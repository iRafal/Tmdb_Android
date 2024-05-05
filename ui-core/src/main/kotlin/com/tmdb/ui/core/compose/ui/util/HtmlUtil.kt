package com.cspace.android.ui.core.compose.ui.util

import android.text.Spanned
import androidx.core.text.HtmlCompat

val String.fromHtml: Spanned
    get() = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT)
