plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
    jacoco
}

android {
    val packageName = "${GradleConfig.Android.applicationId}.ui.core"
    namespace = packageName
    compileSdk = GradleConfig.Android.compileSdk

    defaultConfig {
        minSdk = GradleConfig.Android.minSdk
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "$packageName.runner.HiltTestRunner"
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    lint {
        // https://developer.android.com/studio/write/lint
        baseline = file("lint-baseline.xml")
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
    implementation(libs.hilt)//
    implementation(libs.koin.core)
    implementation(libs.kotlinx.dateTime)
    implementation(libs.logging)
    implementation(project(":store:app-store"))
    implementation(project(":data:model"))
}

fun DependencyHandlerScope.kspDependencies() {
    ksp(libs.hilt.compiler)//
}

fun DependencyHandlerScope.apiDependencies() {
    api(project(":util"))
    api(libs.androidx.core)
    api(libs.androidx.material3.window.size)

    api(libs.androidx.work.runtime)

    api(platform(libs.compose.bom))
    api(libs.compose.animation)
    api(libs.compose.foundation)
    api(libs.compose.runtime)
    api(libs.compose.activity)
    api(libs.compose.constraintLayout)
    api(libs.compose.navigation)
    api(libs.compose.ui)
    api(libs.compose.material.iconsExtended)
    api(libs.compose.ui.preview)
    api(libs.compose.material3)
    api(libs.accompanist.permissions.compose)
    api(libs.compose.viewBinding)

    api(libs.compose.navigation3.runtime)
    api(libs.compose.navigation3.ui)

    api(libs.coil)
    api(libs.coil.compose)
    api(libs.coil.network.okhttp)
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

    androidTestImplementation(libs.hilt.test.android)//
}

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}
