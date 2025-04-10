import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.hilt)
    jacoco
}

android {
    val nameSpace = "${GradleConfig.Android.applicationId}.feature.movie.details.ui"
    namespace = nameSpace
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        testInstrumentationRunner = "$nameSpace}.runner.HiltTestRunner"
        consumerProguardFiles("consumer-rules.pro")
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        debug {
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledDebug
        }
        release {
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledRelease
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = GradleConfig.javaVersion
        targetCompatibility = GradleConfig.javaVersion
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            allWarningsAsErrors = false
        }
    }
    buildFeatures.compose = true
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
}
