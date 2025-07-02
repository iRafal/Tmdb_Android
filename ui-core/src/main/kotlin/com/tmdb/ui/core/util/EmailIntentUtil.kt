package com.tmdb.ui.core.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

@Suppress("SwallowedException")
fun Context.startEmailIntent(
    activityChooserTitle: String? = null,
    onStartIntentFail: (() -> Unit)? = null,
    email: String? = null,
    subject: String? = null,
    body: String? = null
) {
    val uri = if (email.isNullOrBlank()) "mailto:" else "mailto:${email}"

    val intent = Intent(Intent.ACTION_SENDTO, uri.toUri())
    if (!subject.isNullOrBlank()) {
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    }
    if (!body.isNullOrBlank()) {
        intent.putExtra(Intent.EXTRA_TEXT, body)
    }

    try {
        startActivity(Intent.createChooser(intent, activityChooserTitle))
    } catch (e: ActivityNotFoundException) {
        onStartIntentFail?.invoke()
    }
}
