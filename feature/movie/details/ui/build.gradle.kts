plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

android {
    val nameSpace = "${GradleConfig.Android.NAMESPACE}.feature.movie.details.ui"
    namespace = nameSpace
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
        testInstrumentationRunner = "$nameSpace.runner.TestRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    buildFeatures.compose = true
    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
    }
}

dependencies {
    implementationDependencies()
    testImplementation(libs.bundles.feature.ui.impl.test)
    androidTestImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":ui-core"))
    implementation(project(":util"))
    implementation(project(":store:app-store"))

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewModel)
}

fun DependencyHandlerScope.androidTestImplementationDependencies() {
    androidTestImplementation(libs.bundles.ui.test.android)
    androidTestImplementation(libs.koin.test.junit4)
}
