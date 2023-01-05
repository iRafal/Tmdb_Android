import org.gradle.api.JavaVersion


object Libs {

    object BuildConfig {
        const val compileSdk = 33
        const val applicationId = "com.tmdb_test"
        const val minSdk = 26
        const val targetSdk = 33

        object CompileOptions {
            val sourceCompatibility = JavaVersion.VERSION_11
            val targetCompatibility = JavaVersion.VERSION_11
        }

        object KotlinOptions {
            val jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    object SourceSet {
        object Main {
            val name = "main"
            val sourceSets = arrayOf("src/main/java", "src/main/kotlin")
        }

        object Test {
            val name = "test"
            val sourceSets = arrayOf("src/test/java", "src/test/kotlin")
        }

        object AndroidTest {
            val name = "androidTest"
            val sourceSets = arrayOf("src/androidTest/java", "src/androidTest/kotlin")
        }
    }
}