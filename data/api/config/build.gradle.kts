import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

val apiProperties = Properties().apply { load(rootProject.file("api.properties").reader()) }
val apiKey = apiProperties["api.key"].toString()
val apiUrlBase = apiProperties["api.url.base"].toString()
val apiUrlImage = apiProperties["api.url.image"].toString()

val buildConfigApiBaseUrl = "API_BASE_URL"

android {
    namespace = "${GradleConfig.Android.NAMESPACE}.api.config"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "API_KEY", apiKey)
        buildConfigField("String", "API_BASE_URL", apiUrlBase)
        buildConfigField("String", "API_IMAGE_URL", apiUrlImage)
    }

    buildTypes {
        release {
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    buildFeatures.buildConfig = true
    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
    }
}

dependencies {
    implementationDependencies()
    kspDependencies()
    testImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.dagger)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.kotlin.test)
}

fun DependencyHandlerScope.kspDependencies() {
    ksp(libs.dagger.compiler)
}
