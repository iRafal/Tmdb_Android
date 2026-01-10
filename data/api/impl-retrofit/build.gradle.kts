plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    id(libs.plugins.kotlin.serialization.get().pluginId)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.hilt)
    jacoco
}

android {
    namespace = "${GradleConfig.Android.NAMESPACE}.data.api.implRetrofit"
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
    ksp(libs.hilt.compiler)
    testImplementationDependencies()
}

fun DependencyHandlerScope.apiDependencies() {
    api(project(":data:api:model"))
    implementation(project(":data:api:config"))
    implementation(project(":util"))
}

fun DependencyHandlerScope.implementationDependencies() {
    implementation(libs.kotlin.stdLib)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.kotlin.serialization.json)

    implementation(libs.retrofit2)
    implementation(libs.retrofit2.scalars)
    implementation(libs.retrofit2.serializationConverter)
    implementation(libs.okHttp3.loggingInterceptor)

    implementation(libs.hilt)
}

fun DependencyHandlerScope.testImplementationDependencies() {
    testImplementation(libs.kotlin.stdLib)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.coroutines.core)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.serialization.json)
    testImplementation(libs.kotlinx.dateTime)

    testImplementation(libs.retrofit2)
    testImplementation(libs.retrofit2.scalars)
    testImplementation(libs.retrofit2.serializationConverter)
    testImplementation(libs.okHttp3.loggingInterceptor)
    testImplementation(libs.okHttp3.mockwebserver)
}
