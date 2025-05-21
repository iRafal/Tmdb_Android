plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.ksp)
    id(libs.plugins.kotlin.serialization.get().pluginId)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.hilt)
    jacoco
}

android {
    namespace = "${GradleConfig.Android.applicationId}.data.api.implKtor"
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
    ksp(libs.hilt.compiler)
    implementationDependencies()
    testImplementationDependencies()
}

fun DependencyHandlerScope.apiDependencies() {
    api(project(":data:api:model"))
    implementation(project(":data:api:config"))
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(project(":util"))

    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.kotlin.serialization.json)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.logging)

    implementation(libs.hilt)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.kotlin.stdLib)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.coroutines.core)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.serialization.json)

    testImplementation(libs.kotlinx.dateTime)

    testImplementation(libs.ktor.client.mock)
}
