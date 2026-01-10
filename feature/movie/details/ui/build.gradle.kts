plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.hilt)
    jacoco
}

android {
    val nameSpace = "${GradleConfig.Android.NAMESPACE}.feature.movie.details.ui"
    namespace = nameSpace
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
        testInstrumentationRunner = "$nameSpace.runner.HiltTestRunner"
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

    kspDependencies()
    kspAndroidTest(libs.hilt.compiler)

    testImplementation(libs.bundles.feature.ui.impl.test)

    androidTestImplementation(libs.bundles.ui.test.android)
    androidTestImplementation(libs.hilt.test.android)
}

fun DependencyHandlerScope.kspDependencies() {
    ksp(libs.hilt.compiler)
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":ui-core"))
    implementation(project(":util"))
    implementation(project(":store:app-store"))

    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
}
