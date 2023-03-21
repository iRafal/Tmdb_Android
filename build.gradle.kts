buildscript {
    repositories {
        google()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenCentral()
        google()
    }

    dependencies {
        classpath(libs.gradle)
        classpath(libs.ktlint.plugin)
        classpath(libs.kotlin.gradle)
        classpath(libs.kotlin.serialization)
        classpath(libs.hilt.plugin)
        classpath(libs.realm.plugin)
        classpath(libs.objectBox)
    }
}

/**
 * Mac
 * ./gradlew ktlintCheck
 * ./gradlew ktlintFormat
 *
 * Windows
 * gradlew ktlintCheck
 * ./gradlew ktlintFormat
 */

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent

    repositories {
        // Required to download KtLint
        mavenCentral()
    }

    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
