import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.hilt)
    jacoco
}

val apiProperties = Properties().apply { load(rootProject.file("api.properties").reader()) }
val apiKey = apiProperties["api.key"].toString()
val apiUrlBase = apiProperties["api.url.base"].toString()
val apiUrlImage = apiProperties["api.url.image"].toString()

val buildConfigApiBaseUrl = "API_BASE_URL"

android {
    namespace = "${GradleConfig.Android.applicationId}.api.config"
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "API_KEY", apiKey)
        buildConfigField("String", "API_BASE_URL", apiUrlBase)
        buildConfigField("String", "API_IMAGE_URL", apiUrlImage)
    }

    buildTypes {
        debug {
            isMinifyEnabled = GradleConfig.Android.isMinifyEnabledDebug
            isDefault = true
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
    buildFeatures.buildConfig = true
}

dependencies {
    implementationDependencies()
    kspDependencies()
    testImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.dagger) //
    implementation(libs.hilt)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.kotlin.test)
}

fun DependencyHandlerScope.kspDependencies() {
    ksp(libs.dagger.compiler) //
    ksp(libs.hilt.compiler)
}
