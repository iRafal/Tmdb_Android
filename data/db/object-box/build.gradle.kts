import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.ksp)
    id(libs.plugins.objectbox.get().pluginId)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

android {
    namespace = "${GradleConfig.Android.applicationId}.data.db.objectBox"
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures.buildConfig = true

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
}

dependencies {
    implementationDependencies()
    kspDependencies()
    testImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":util"))
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.dagger)
    implementation(libs.kotlinx.dateTime)
    implementation(libs.logging)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlinx.dateTime)
}

fun DependencyHandlerScope.kspDependencies() {
    ksp(libs.dagger.compiler)
}