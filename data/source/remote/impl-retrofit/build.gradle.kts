import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.ksp)
    jacoco
}

android {
    namespace = "${GradleConfig.Android.applicationId}.data.source.remote.impl"
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
    ksp(libs.dagger.compiler)
    apiDependencies()
    testImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.kotlinx.dateTime)
    implementation(libs.dagger)
    implementation(project(":data:api:impl-retrofit"))
    implementation(project(":data:api:config"))
    implementation(project(":util"))
}

fun DependencyHandlerScope.apiDependencies() {
    api(project(":data:source:remote:contract"))
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlinx.dateTime)
}
