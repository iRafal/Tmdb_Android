plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    id(libs.plugins.kotlin.serialization.get().pluginId)
    alias(libs.plugins.kotlinx.kover)
    jacoco
}

android {
    namespace = "${GradleConfig.Android.NAMESPACE}.data.api.implKtor"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
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

    implementation(libs.koin.core)
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
