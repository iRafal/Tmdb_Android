import org.gradle.api.JavaVersion

object GradleConfig {

    object Android {
        const val NAMESPACE = "com.tmdb"
    }

    val excludePackagingResources = arrayOf(
        "/META-INF/{AL2.0,LGPL2.1}",
        "/META-INF/gradle/incremental.annotation.processors"
    )

    object App {
        const val ID = "com.tmdb"
        val version = Version(major = 1, minor = 0, patch = 0)
        const val VERSION_CODE = 61
    }

    val javaVersion = JavaVersion.VERSION_21
    val javaVersionAsString = javaVersion.toString()

    object Plugins {
        const val KOTLIN_KAPT = "kotlin-kapt"
    }
}
