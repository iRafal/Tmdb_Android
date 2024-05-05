package com.tmdb.util

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tmdb.R
import java.io.File

/***
 * Taken from
 * https://mvnrepository.com/artifact/com.google.firebase/firebase-crashlytics-ktx
 * https://github.com/firebase/firebase-android-sdk/blob/master/firebase-crashlytics/src/main/java/com/google/firebase/crashlytics/internal/common/CommonUtils.java
 */
internal object RootUtils {
    private const val GOLDFISH: String = "goldfish"
    private const val RANCHU: String = "ranchu"
    private const val SDK: String = "sdk"

    private val isEmulator: Boolean
        get() = Build.PRODUCT.contains(SDK) || Build.HARDWARE.contains(GOLDFISH) || Build.HARDWARE.contains(RANCHU)

    val isRooted: Boolean
        get() {
            // No reliable way to determine if an android phone is rooted, since a rooted phone could
            // always disguise itself as non-rooted. Some common approaches can be found on SO:
            // http://stackoverflow.com/questions/1101380/determine-if-running-on-a-rooted-device
            // http://stackoverflow.com/questions/3576989/how-can-you-detect-if-the-device-is-rooted-in-the-app
            // http://stackoverflow.com/questions/7727021/how-can-androids-copy-protection-check-if-the-device-is-rooted
            if (!isEmulator && Build.TAGS?.contains("test-keys") == true) return true

            // Superuser.apk would only exist on a rooted device:
            val superUserFile = File("/system/app/Superuser.apk")
            if (superUserFile.exists()) return true

            // su is only available on a rooted device (or the emulator)
            // The user could rename or move to a non-standard location, but in that case they
            // probably don't want us to know they're root and they can pretty much subvert
            // any check anyway.
            val suFile = File("/system/xbin/su")
            return !isEmulator && suFile.exists()
        }
}

internal fun Activity.showRootDeviceErrorAlert() {
    val dialog = MaterialAlertDialogBuilder(this)
        .setMessage(R.string.root_device_warning)
        .setCancelable(false)
        .setPositiveButton(android.R.string.ok) { _, _ -> finish() }
        .create()

    // Overriding positive button to not dismiss a dialog after a click
    // https://stackoverflow.com/a/15619098/6430090
    dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener { finish() }

    dialog.show()
}
