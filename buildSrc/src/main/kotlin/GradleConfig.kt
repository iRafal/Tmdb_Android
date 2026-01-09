import org.gradle.api.JavaVersion

object GradleConfig {

    object Android {
        const val compileSdk = 36
        const val applicationId = "com.tmdb"
        const val minSdk = 26
        const val targetSdk = 36

        const val isMinifyEnabledDebug = false
        const val isMinifyEnabledRelease = true

        const val isShrinkResourcesDebug = false
        const val isShrinkResourcesRelease = true

        val excludePackagingResources = arrayOf(
            "/META-INF/{AL2.0,LGPL2.1}",
            "/META-INF/gradle/incremental.annotation.processors"
        )
    }

    val javaVersion = JavaVersion.VERSION_21
    val javaVersionAsString = javaVersion.toString()

    object Plugins {
        const val KOTLIN_KAPT = "kotlin-kapt"
    }

    object Flavor {
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
