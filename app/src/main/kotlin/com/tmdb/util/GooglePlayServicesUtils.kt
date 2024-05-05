package com.tmdb.util

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tmdb.R

internal fun Activity.isGooglePlayServicesAvailable(): Boolean {
    val instance = GoogleApiAvailability.getInstance()
    val status = instance.isGooglePlayServicesAvailable(this)
    return status == ConnectionResult.SUCCESS
}

internal fun Activity.checkGooglePlayServicesAvailability(): Boolean {
    val instance = GoogleApiAvailability.getInstance()
    val status = instance.isGooglePlayServicesAvailable(this)
    if (status == ConnectionResult.SUCCESS) return true

    if (instance.isUserResolvableError(status)) {
        instance.getErrorDialog(this, status, 0)?.let {
            it.setOnDismissListener { finish() }
            it.setCancelable(false)
            it.show()
        }
    } else {
        showGooglePlayServicesAvailabilityErrorAlert()
    }

    return false
}

internal fun Activity.showGooglePlayServicesAvailabilityErrorAlert() {
    val dialog =  MaterialAlertDialogBuilder(this)
        .setMessage(R.string.missed_google_play_services_warning)
        .setCancelable(false)
        .setPositiveButton(android.R.string.ok) { _, _ -> finish() }
        .create()

    // Overriding positive button to not dismiss a dialog after a click
    // https://stackoverflow.com/a/15619098/6430090
    dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener { finish() }

    dialog.show()
}
