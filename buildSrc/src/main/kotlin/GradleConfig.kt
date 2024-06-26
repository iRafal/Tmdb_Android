import org.gradle.api.JavaVersion

object GradleConfig {

    object Android {
        const val compileSdk = 34
        const val applicationId = "com.tmdb"
        const val minSdk = 26
        const val targetSdk = 34

        const val isMinifyEnabledDebug = false
        const val isMinifyEnabledRelease = true

        const val isShrinkResourcesDebug = false
        const val isShrinkResourcesRelease = true

        val excludePackagingResources = arrayOf(
            "/META-INF/{AL2.0,LGPL2.1}",
            "/META-INF/gradle/incremental.annotation.processors"
        )
    }

    val javaVersion = JavaVersion.VERSION_17
    val javaVersionAsString = javaVersion.toString()

    object Plugins {
        const val ANDROID_LIBRARY = "com.android.library"
        const val ANDROID_APPLICATION = "com.android.application"
        const val KOTLIN_ANDROID = "kotlin-android"
        const val KOTLIN_ANDROID_EXTENDED_NAME = "org.jetbrains.kotlin.android"
        const val KOTLIN = "kotlin"
        const val KOTLIN_JVM = "org.jetbrains.kotlin.jvm"
        const val KOTLIN_KAPT = "kotlin-kapt"
        const val JAVA_LIBRARY = "java-library"
        const val HILT = "dagger.hilt.android.plugin"
        const val KOTLINX_SERIALIZATION = "kotlinx-serialization"
        const val KOTLINX_SERIALIZATION_EXTENDED_NAME = "org.jetbrains.kotlin.plugin.serialization"
        const val REALM = "realm-android"
        const val OBJECTBOX = "io.objectbox"
    }

    object Flavor {
        object API {
            const val NAME = "api"
            object Options {
                const val PROD = "prod"
                const val PROD_QA = "prodQa"
                const val STAGING_TEST = "stagingTest"
                const val QA_TCP = "qaTcp"
            }
        }
        object Data {
            object Source {
                const val NAME = "data_source"
                object Options {
                    const val API = "api"
                    const val MOCK = "mock"
                }
            }
        }
    }
}
