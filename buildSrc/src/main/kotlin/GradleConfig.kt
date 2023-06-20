import org.gradle.api.JavaVersion


object GradleConfig {

    object Android {
        const val compileSdk = 33
        const val applicationId = "com.tmdb"
        const val minSdk = 26
        const val targetSdk = 33

        const val isMinifyEnabledDebug = false
        const val isMinifyEnabledRelease = false

        const val isShrinkResourcesDebug = false
        const val isShrinkResourcesRelease = false

        val excludePackagingResources = arrayOf("/META-INF/{AL2.0,LGPL2.1}", "/META-INF/gradle/incremental.annotation.processors")
    }

    val javaVersion = JavaVersion.VERSION_17
    val javaVersionAsString = javaVersion.toString()

    object Plugins {
        const val ANDROID_LIBRARY = "com.android.library"
        const val ANDROID_APPLICATION = "com.android.application"
        const val KOTLIN_ANDROID = "kotlin-android"
        const val KOTLIN = "kotlin"
        const val KOTLIN_JVM = "org.jetbrains.kotlin.jvm"
        const val KOTLIN_KAPT = "kotlin-kapt"
        const val JAVA_LIBRARY = "java-library"
        const val HILT = "dagger.hilt.android.plugin"
        const val KOTLINX_SERIALIZATION = "kotlinx-serialization"
        const val REALM = "realm-android"
        const val OBJECTBOX = "io.objectbox"
    }
}
