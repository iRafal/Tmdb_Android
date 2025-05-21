import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.hilt)
    jacoco
}

android {
    namespace = "${GradleConfig.Android.applicationId}.data.source.local.impl"
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledDebug
        }
        release {
            consumerProguardFiles("consumer-rules.pro")
        }
    }
    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
    }
}

dependencies {
    apiDependencies()
    implementationDependencies()
    ksp(libs.hilt.compiler)
    testImplementation(libs.bundles.data.source.local.impl.test)
}

fun DependencyHandlerScope.apiDependencies() {
    api(project(":data:source:local:contract"))
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.hilt)
    implementation(project(":data:db:room"))
    implementation(project(":util"))
    implementation(libs.bundles.data.source.local.impl)
}
