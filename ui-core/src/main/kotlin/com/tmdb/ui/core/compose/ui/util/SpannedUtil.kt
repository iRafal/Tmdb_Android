package com.cspace.android.ui.core.compose.ui.util

import android.text.Spanned
import android.text.style.UnderlineSpan
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration

fun Spanned.toAnnotatedString(textColor: Color): AnnotatedString = buildAnnotatedString {
    val spanned = this@toAnnotatedString
    append(spanned.toString())
    getSpans(0, spanned.length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is UnderlineSpan -> {
                addStyle(SpanStyle(textDecoration = TextDecoration.Underline, color = textColor), start, end)
            }
        }
    }
}
