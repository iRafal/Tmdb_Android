plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    id(GradleConfig.Plugins.KOTLIN_KAPT)
    id(GradleConfig.Plugins.KOTLINX_SERIALIZATION)
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
    kotlinOptions.jvmTarget = GradleConfig.javaVersionAsString
}

dependencies {
    apiDependencies()
    kapt(libs.dagger.compiler)
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

    implementation(libs.dagger)
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
