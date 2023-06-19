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

    object SourceSet {
        object Main {
            const val name = "main"
            val sourceSets = arrayOf("src/main/java", "src/main/kotlin")
        }

        object Test {
            const val name = "test"
            val sourceSets = arrayOf("src/test/java", "src/test/kotlin")
        }

        object AndroidTest {
            const val name = "androidTest"
            val sourceSets = arrayOf("src/androidTest/java", "src/androidTest/kotlin")
        }
    }
}