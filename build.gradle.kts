buildscript {
    repositories {
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
        classpath(libs.detekt)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }

    /**
     * ./gradlew detekt
     */
    apply(plugin = "io.gitlab.arturbosch.detekt")
}

/**
 * Mac
 * chmod +x gradlew (id needed, https://stackoverflow.com/questions/17668265/gradlew-permission-denied)
 * ./gradlew ktlintCheck
 * ./gradlew ktlintFormat
 *
 * Windows
 * gradlew ktlintCheck
 * gradlew ktlintFormat
 */


subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent

    repositories {
        // Required to download KtLint
        mavenCentral()
    }

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        verbose.set(true)
        android.set(true)

        outputToConsole.set(true)
        outputColorName.set("RED")

        relative.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(false) // https://github.com/pinterest/ktlint/blob/master/ktlint-ruleset-experimental/src/main/kotlin/com/pinterest/ktlint/ruleset/experimental/ExperimentalRuleSetProvider.kt
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.HTML)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        }
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
    jvmTarget = GradleConfig.javaVersionAsString
}

tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = GradleConfig.javaVersionAsString
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}