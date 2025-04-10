import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    val packageName = "${GradleConfig.Android.applicationId}.ui.core"
    namespace = packageName
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "$packageName.runner.DaggerTestRunner"
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementationDependencies()
    kspDependencies()
    apiDependencies()
    debugApiDependencies()
    testImplementationDependencies()
    androidTestImplementationDependencies()
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.dagger)
    implementation(libs.kotlinx.dateTime)
    implementation(libs.logging)
    implementation(project(":store:app-store"))
    implementation(project(":data:model"))
}

fun DependencyHandlerScope.kspDependencies() {
    ksp(libs.dagger.compiler)
}

fun DependencyHandlerScope.apiDependencies() {
    api(project(":util"))
    api(libs.androidx.core)
    api(libs.androidx.material3.window.size)

    api(libs.androidx.work.runtime)

    api(libs.compose.animation)
    api(libs.compose.compiler)
    api(libs.compose.foundation)
    api(libs.compose.runtime)
    api(libs.compose.activity)
    api(libs.compose.constraintLayout)
    api(libs.compose.navigation)
    api(libs.compose.ui)
    api(libs.compose.ui.preview)
    api(libs.compose.material.iconsExtended)
    api(libs.compose.material3)

    api(libs.coil)
    api(libs.coil.compose)
    api(libs.coil.network.okHttp)
//    api(libs.coil.network.ktor)
    api(libs.okHttp3.loggingInterceptor)
//    api(libs.ktor.client.core)
//    api(libs.ktor.client.android)

    api(libs.kotlinx.dateTime)
}

fun DependencyHandlerScope.debugApiDependencies() {
    debugApi(libs.compose.ui.tooling.debug)
    debugImplementation(libs.compose.ui.test.manifest.debug)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.coroutines.test)

    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
}

fun DependencyHandlerScope.androidTestImplementationDependencies() {
    androidTestImplementation(libs.junit.android.ext)
    androidTestImplementation(libs.espresso)

    androidTestImplementation(libs.kotlin.test)
    androidTestImplementation(libs.kotlin.coroutines.test)

    androidTestImplementation(libs.compose.ui.test.manifest.debug)
    androidTestImplementation(libs.compose.ui.test.junit)
    androidTestImplementation(libs.compose.navigation.test)
}
